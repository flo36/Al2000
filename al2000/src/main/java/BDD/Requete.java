package BDD;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import Cinema.Film;
import Donnee.SQLException;

import static BDD.Parser.resultSetToArray;

/**
 INTERFACE :
  --JE CONSIDERE ICI QUE LES OBJETS "FILM" ET "USER" SONT PARSES SOUS FORMAT STRING--

 getFilmList()
 -> RÃ©cupere la liste des films

 getFilm(String nom)
 -> RecupÃ¨re les donnÃ©es d'un film

 getAvailableFilmList(String nom)
 -> RÃ©cupere la liste des films stockÃ©s dans la machine

 modifyFilmAvailable(int id)
 -> emprunte ou rend un film

 addFilm(String nom)
 -> ajoute le DVD dans la liste (attention pas de vÃ©rification de quantitÃ©)
 deleteFilm(int id)
 -> supprime le DVD de la liste

 getUser(int id)
 -> rÃ©cupÃ¨re les infos d'un utilisateur

 setUser(String user)
 -> inscrit les infos d'un utilisateur
 */


public class Requete {

    final static String FILM_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
    final static String USER_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";

    public static ArrayList<String> getFilmList() {
        ResultSet rs = new InteractionBaseOracle(FILM_URL).sendRequest("SELECT nomF FROM LesFilms");
        return resultSetToArray(rs);
    }

    public static ArrayList<String> getFilm(String name, String real) {
        ResultSet rs = new InteractionBaseOracle(FILM_URL).sendRequest("SELECT * from LesFilms where nomF = '" + name + "' AND realisateur = '" + real + "'");

        //ArrayList<String> rs2 = new InteractionBaseLocale().sendRequest("SELECT * FROM LesFilmsDisponibles WHERE nomF = " + name);
        ArrayList<String> rs2 = new InteractionBaseLocale().sendRequest("SELECT" + name + " " + real);

        return Parser.bddToStringArrayFilm(rs, rs2);
    }


    public static ArrayList<String> getAvailableFilmList() {
        return new InteractionBaseLocale().sendRequest("SELECT nomF FROM LesFilmsDisponibles");
    }

    public static int modifyFilmAvailable(int id) {
        //return new InteractionBaseLocale().sendUpdate("UPDATE LesFilmsDisponibles SET available = !available WHERE idF = " + id);
        return new InteractionBaseLocale().sendUpdate("UPDATE " + id);

    }

    public static int deleteFilm(int oldfilm) {
        //return new InteractionBaseLocale().sendUpdate("DELETE FROM LesFilmsDisponibles WHERE idF = " + oldfilm);
        return new InteractionBaseLocale().sendUpdate("DELETE " + oldfilm);

    }

    public static int addFilm(String newfilmname, String newfilmreal) {
        //return new InteractionBaseLocale().sendUpdate("INSERT INTO LesFilmsDisponibles VALUES ('" + newfilmname + "', '" + newfilmreal + "')");
        return new InteractionBaseLocale().sendUpdate("INSERT " + newfilmname + " " + newfilmreal);

    }

    public static ArrayList<String> getUser(int cb) {
        ResultSet rs = new InteractionBaseOracle(USER_URL).sendRequest("SELECT * from LesClients where CB = " + cb);
        return resultSetToArray(rs);
    }

    public static int setUser(String user) {
        return new InteractionBaseOracle(USER_URL).sendUpdate("INSERT INTO LesClients VALUES (" + user + ")");
    }
    
    public ArrayList<Film> getFilms(String sql) {
		InteractionBaseOracle bdd = new InteractionBaseOracle(
				"jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521/IM2AG", "nom_user", "mdp_user");


		ArrayList<Film> films = new ArrayList<Film>();
		
		try {
			bdd.connect();
			ResultSet rs = bdd.sendRequest(sql);
			while (rs.next()) {
				String titre = rs.getString("titre");
				DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
				java.util.Date annee = null;
				try {
					annee = format.parse(rs.getString("annee"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				int agemini = Integer.parseInt(rs.getString("agemini"));
				String resume = rs.getString("synopsis");
				int duree = Integer.parseInt(rs.getString("duree"));
				films.add(new Film(titre,annee,agemini,resume,duree));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			bdd.disconnect();
		}
		return films;
	}



}
