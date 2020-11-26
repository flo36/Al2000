package src.main.java.BDD;

//import Model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.main.java.Cinema.Genre;

/**
 INTERFACE :
  --JE CONSIDERE ICI QUE LES OBJETS "FILM" ET "USER" SONT PARSES SOUS FORMAT STRING--

 getFilmList()
 -> RÃ©cupere la liste des films

 getFilm(String nom)
 -> RecupÃ¨re les donnÃ©es d'un film
 
 getFilms(String nom)
 -> Récupère (au maximum) 10 films ayant la chaîne de caractères "nom" dans leur titre 
 
 getFilmsGenre(Genre genre)
 ->Récupère les films ayant le genre donné en paramètre

 getFilmsActeur(String nom)
 ->Récupère les films où l'acteur (avec le nom passé en paramètre) joue
 
 getFilmsRealisateur(String nom)
 ->Récupère les films où le réalisateur est "nom"

 getAvailableFilmList(String nom)
 -> RÃ©cupere la liste des films stockÃ©s dans la machine

 modifyFilmAvailable(int id)
 -> emprunte ou rend un film

 addFilm(String nom)
 -> ajoute le DVD dans la liste (attention pas de vÃ©rification de quantitÃ©)
 deleteFilm(int id)
 -> supprime le DVD de la liste

 getUser(int cb)
 -> rÃ©cupÃ¨re les infos d'un utilisateur grâce au numéro de sa cb (clef primaire)

 setUser(String user)
 -> inscrit les infos d'un utilisateur
 */


public class Requete {

    final static String FILM_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
    final static String USER_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";

    public static ArrayList<String> getFilmList() {
        ResultSet rs = new InteractionBaseOracle(FILM_URL).sendRequest("SELECT nomF FROM LesFilms");
        return resultSetToArray1(rs);
    }
    
   /* public static ArrayList<String> getFilms(String name){
        ResultSet rs = new InteractionBaseOracle(FILM_URL).sendRequest("SELECT * from LesFilmsDisponibles where nomF LIKE '%" + name + "%' LIMIT 10");
        return resultSetToArray1(rs);
    }*/
    
    public static ArrayList<String> getFilms(String name){
        ResultSet rs = new InteractionBaseOracle(FILM_URL).sendRequest("SELECT titre from LesFilms where titre LIKE '%"+name+"%' AND titre NOT IN ( SELECT titre from Locations where rendu = false) LIMIT 10;");
        return resultSetToArray1(rs);
    }
    
    public static ArrayList<String> getFilmsGenre(Genre genre){
        ResultSet rs = new InteractionBaseOracle(FILM_URL).sendRequest("SELECT * from LesFilmsDisponibles where genre = '"+genre+"' LIMIT 10");
        return resultSetToArray1(rs);
    }
    
    public static ArrayList<String> getFilmsActeurs_Realisateurs(String name){
        ResultSet rs = new InteractionBaseOracle(FILM_URL).sendRequest("SELECT * from LesFilms INNER JOIN Participe on LesFilms.titre = Participe.titre and LesFilms.date = Participe.annee where id_celebrite IN (SELECT id from LesCelebrites where nom LIKE '%"+name+"%' ");
        return resultSetToArray1(rs);
    }
    
    public static ArrayList<String> getFilm(String name) {
        ResultSet rs = new InteractionBaseOracle(FILM_URL).sendRequest("SELECT * from LesFilms where nomF = '" + name + "'");
        ArrayList<String> rs2 = new InteractionBaseLocale().sendRequest("SELECT * FROM LesFilmsDisponibles WHERE nomF = " + name);

        return Parser.bddToStringArrayFilm(rs, rs2);
    }


    public static ArrayList<String> getAvailableFilmList() {
        return new InteractionBaseLocale().sendRequest("SELECT nomF FROM LesFilmsDisponibles");
    }

    public static int modifyFilmAvailable(int id) {
        return new InteractionBaseLocale().sendUpdate("UPDATE LesFilmsDisponibles SET available = !available WHERE idF = " + id);
    }

    public static int deleteFilm(int oldfilm) {
        return new InteractionBaseLocale().sendUpdate("DELETE FROM LesFilmsDisponibles WHERE idF = " + oldfilm);
    }

    public static int addFilm(String newfilm) {
        return new InteractionBaseLocale().sendUpdate("INSERT INTO LesFilmsDisponibles VALUES ('" + newfilm + "')");
    }

    public static ArrayList<String> getUser(int cb) {
        ResultSet rs = new InteractionBaseOracle(USER_URL).sendRequest("SELECT * from LesClients where CB = " + cb);
        return resultSetToArray1(rs);
    }

    public static int setUser(String user) {
        return new InteractionBaseOracle(USER_URL).sendUpdate("INSERT INTO LesClients VALUES (" + user + ")");
    }


    private static ArrayList<String> resultSetToArray1(ResultSet rs) {
        ArrayList<String> l = new ArrayList<>();

        while (true) {
            try {
                if (!rs.next()) break;
                l.add(rs.getString(1));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return l;
    }
}
