package main.java.BDD;

//import static main.java.BDD.Parser.resultSetToArray;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import main.java.Cinema.Dvd;
import main.java.Cinema.Film;
import main.java.Client.Abonne;
import main.java.Client.Location;
import main.java.Client.NonAbonne;
import main.java.Client.Utilisateur;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * INTERFACE : --JE CONSIDERE ICI QUE LES OBJETS "FILM" ET "USER" SONT PARSES
 * SOUS FORMAT STRING--
 * 
 * getFilmList() -> RÃ©cupere la liste des films
 * 
 * getFilm(String nom) -> RecupÃ¨re les donnÃ©es d'un film
 * 
 * getAvailableFilmList(String nom) -> RÃ©cupere la liste des films stockÃ©s
 * dans la machine
 * 
 * modifyFilmAvailable(int id) -> emprunte ou rend un film
 * 
 * addFilm(String nom) -> ajoute le DVD dans la liste (attention pas de
 * vÃ©rification de quantitÃ©) deleteFilm(int id) -> supprime le DVD de la liste
 * 
 * getUser(int id) -> rÃ©cupÃ¨re les infos d'un utilisateur
 * 
 * setUser(String user) -> inscrit les infos d'un utilisateur
 */

public class Requete {

    final static String FILM_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
    final static String USER_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
    String mdp = readProp("login", "password");
    String login = readProp("login", "username");

    public ArrayList<Film> getFilmList() throws SQLException {
        ResultSet rs = new InteractionBaseOracle(FILM_URL, login, mdp).sendRequest("SELECT titre,annee FROM LesFilms");
        ArrayList<Film> res = new ArrayList<Film>();
        while (rs.next()) {
            // le client rs est un abonne
            Film f = new Film(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
            res.add(f);
        }

        return res;
    }

    public ArrayList<String> getFilm(String name, String real) {
        ResultSet rs = new InteractionBaseOracle(FILM_URL, login, mdp)
                .sendRequest("SELECT * from LesFilms where nomF = '" + name + "' AND realisateur = '" + real + "'");

        // ArrayList<String> rs2 = new InteractionBaseLocale().sendRequest("SELECT *
        // FROM LesFilmsDisponibles WHERE nomF = " + name);
        ArrayList<String> rs2 = new InteractionBaseLocale().sendRequest("SELECT" + name + " " + real);

        return Parser.bddToStringArrayFilm(rs, rs2);
    }

    public ArrayList<Film> getAvailableFilmList() throws SQLException {
        ResultSet rs = new InteractionBaseOracle(FILM_URL, login, mdp)
                .sendRequest("SELECT nomF FROM LesFilmsDisponibles");
        ArrayList<Film> res = new ArrayList<Film>();
        while (rs.next()) {
            // le client rs est un abonne
            Film f = new Film(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
            res.add(f);
        }

        return res;
    }

    public int modifyFilmAvailable(int id) {
        // return new InteractionBaseLocale().sendUpdate("UPDATE LesFilmsDisponibles SET
        // available = !available WHERE idF = " + id);
        return new InteractionBaseLocale().sendUpdate("UPDATE " + id);

    }

    public int deleteFilm(int oldfilm) {
        // return new InteractionBaseLocale().sendUpdate("DELETE FROM
        // LesFilmsDisponibles WHERE idF = " + oldfilm);
        return new InteractionBaseLocale().sendUpdate("DELETE " + oldfilm);

    }

    public int addFilm(String newfilmname, String newfilmreal) {
        // return new InteractionBaseLocale().sendUpdate("INSERT INTO
        // LesFilmsDisponibles VALUES ('" + newfilmname + "', '" + newfilmreal + "')");
        return new InteractionBaseLocale().sendUpdate("INSERT " + newfilmname + " " + newfilmreal);

    }

    public ArrayList<Abonne> getAbo(int cb) throws SQLException {
        ResultSet rs = new InteractionBaseOracle(USER_URL, login, mdp)
                .sendRequest("SELECT * from LesClients where CB = " + cb + " and abonne = true");

        ArrayList<Abonne> res = new ArrayList<Abonne>();
        while (rs.next()) {
            // le client rs est un abonne
            Abonne user = new Abonne(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            user.setSolde(rs.getInt(6));
            res.add(user);
        }

        return res;
    }

    public ArrayList<NonAbonne> getNabo(int cb) throws SQLException {
        ResultSet rs = new InteractionBaseOracle(USER_URL, login, mdp)
                .sendRequest("SELECT * from LesClients where CB = " + cb + " and abonne = false");

        ArrayList<NonAbonne> res = new ArrayList<NonAbonne>();
        while (rs.next()) {
            // le client rs est un abonne
            NonAbonne user = new NonAbonne(rs.getInt(1));
            res.add(user);
        }
        return res;
    }

    public int setUser(String user) {
        return new InteractionBaseOracle(USER_URL, login, mdp)
                .sendUpdate("INSERT INTO LesClients VALUES (" + user + ")");
    }

    // Ajouter une location : prend en parametre un client et un dvd !!!!!!! penser
    // � l auto incr�mentation des id !!!!!!
    public int addLocation(Utilisateur user, Dvd d) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new java.util.Date());

        return new InteractionBaseOracle(USER_URL, login, mdp)
                .sendUpdate("INSERT INTO LesLocations(titre, annee, cb_client, date_emprunt, rendu) values ("
                        + d.getFilm().getTitre() + ", " + d.getFilm().getTitre() + " ,'" + date + "', false)");
    }

    // fin d une location : prend en parametre un dvd et client
    public int finLocation(Dvd d, Utilisateur user) {
        return new InteractionBaseOracle(USER_URL, login, mdp)
                .sendUpdate("UPDATE LesLocations SET rendu = true where titre = '" + d.getFilm().getTitre()
                        + "' AND annee = '" + d.getFilm().getAnnee() + "' AND cb_client = " + user.getCarteBleue());
    }

    // Voir l historique : prend en parametre un client
    public ArrayList<Location> recupHistorique(Utilisateur user) throws SQLException {
        ResultSet rs = new InteractionBaseOracle(USER_URL, login, mdp)
                .sendRequest("SELECT * FROM LesLocations where cb_client = " + user.getCarteBleue());

        ArrayList<Location> res = new ArrayList<Location>();
        while (rs.next()) {
            // le client rs est un abonne
            Location loc = new Location(rs.getInt(1), user, new Dvd(new Film(rs.getString(2), rs.getDate(3))),
                    rs.getString(4));
            res.add(loc);
        }
        return res;
    }

    public ArrayList<Film> getFilms(String sql) {
        InteractionBaseOracle bdd = new InteractionBaseOracle(
                "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521/IM2AG", login, mdp);

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
                films.add(new Film(titre, annee, agemini, resume, duree));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bdd.disconnect();
        }
        return films;
    }

    public static String readProp(String filename, String property) {

        Properties properties = new Properties();
        String prop = new String();

        try (InputStream inputStream = new FileInputStream(filename)) {

            properties.load(inputStream);
            prop = properties.getProperty(property);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (prop == null) {
            throw new RuntimeException("valeure de '" + property + "'est null, veuillez verifier le fichier login");
        }
        return prop;
    }

}
