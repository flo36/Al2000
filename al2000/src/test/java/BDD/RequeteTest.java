package BDD;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

class RequeteTest {

    @Test
    void getFilmList() {
        //Requete.getFilmList();
    }

    @Test
    void getFilm() {
    }

    @Test
    void getAvailableFilmList() {
        ArrayList<String> s = Requete.getAvailableFilmList();
        assertNotNull(s);
        assertEquals("Colorful",s.get(0));
    }

    @Test
    void modifyFilmAvailable() {
        ArrayList<String> filmavant = Requete.getFilm("Colorful", "Keiichi Hara");
        int filmavantid = parseInt(filmavant.get(filmavant.size()-3).substring(1));
        Boolean filmavantdispo = Boolean.parseBoolean(filmavant.get(filmavant.size()-1));

        Requete.modifyFilmAvailable(filmavantid);

        ArrayList<String> filmapres = Requete.getFilm("Colorful","Keiichi Hara");
        int filmapresid = parseInt(filmapres.get(filmapres.size()-3).substring(1));
        Boolean filmapresdispo = Boolean.parseBoolean(filmapres.get(filmapres.size()-1));

        assertEquals(filmavant.size(),filmapres.size());
        assertEquals(filmavantid,filmapresid);
        assertNotEquals(filmavantdispo,filmapresdispo);

    }

    @Test
    void deleteFilm() {
        ArrayList<String> filmsavant = Requete.getAvailableFilmList();
        ArrayList<String> filmavant = Requete.getFilm(filmsavant.get(filmsavant.size()-2),filmsavant.get(filmsavant.size()-1));

        Requete.deleteFilm(parseInt(filmavant.get(filmavant.size()-3).substring(1)));

        ArrayList<String> filmsapres = Requete.getAvailableFilmList();
        ArrayList<String> filmapres = Requete.getFilm(filmsavant.get(filmsavant.size()-2),filmsavant.get(filmsavant.size()-1));

        assertEquals(filmsavant.size(),filmsapres.size() +1);
        assertNotEquals(filmavant.size(),filmapres.size());
    }

    /*
    @Test
    void addFilm() {
        ArrayList<String> filmsavant = Requete.getAvailableFilmList();
        ArrayList<String> filmavant = Requete.getFilm(filmsavant.get(filmsavant.size()-2),filmsavant.get(filmsavant.size()-1));

        Requete.addFilm("Patient", "Fabien Marsaux");

        ArrayList<String> filmsapres = Requete.getAvailableFilmList();
        ArrayList<String> filmapres = Requete.getFilm(filmsavant.get(filmsavant.size()-2),filmsavant.get(filmsavant.size()-1));

        assertEquals(filmsavant.size(),filmsapres.size() -1);
        assertNotEquals(filmavant.size(),filmapres.size());

    }
    */

    @Test
    void getUser() {
    }

    @Test
    void setUser() {
    }
}