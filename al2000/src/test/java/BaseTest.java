import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import BDD.InteractionBaseOracle;
import BDD.MaBDD;
import Cinema.*;
import Donnee.ALMediator;

class BaseTest {
    /*
    @Test
    void tTest() {
        InteractionBaseOracle bdd = new InteractionBaseOracle(
                "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521/IM2AG","ayariw","cQtPc54VbL");

        try {
            bdd.connect();
            ResultSet rs = bdd.sendRequest("SELECT * FROM LesFilms");
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            for(int i=1;i<=colCount;i++){
                System.out.print(meta.getColumnName(i) + " ");
            }
            System.out.println("");
            while (rs.next()) {
                String titre = rs.getString("titre");
                String annee = rs.getString("annee");
                String agemini = rs.getString("agemini");
                String synopsis = rs.getString("synopsis");
                String duree = rs.getString("duree");
                System.out.println(titre + " " + annee + " " + agemini + " " + synopsis + " " + duree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bdd.disconnect();
        }
        assertTrue(true);

    }
    */

    @Test
    void tTest2() {
        ALMediator mediateur = new ALMediator();
        ArrayList<Film> films = mediateur.getFilms("SELECT * FROM LesFilms WHERE agemini >= 13");
        for(Film f : films){
            System.out.println(f);
        }
    }
}
