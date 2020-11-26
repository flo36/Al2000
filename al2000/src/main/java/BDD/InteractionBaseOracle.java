package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 Interface :
 sendRequest(String requeteSQL)
 -> Envoie la requete et retourne un resultset
 sendUpdate(String requeteSQL)
 -> Envoie la mise à jour et retourne le nombre de changements
 */

public class InteractionBaseOracle {
    InteractionBaseOracle(String url){
        super();
        CONN_URL = url;
    }

    private final String CONN_URL;
    private final String USER = "test";
    private final String PASSWD = "pw";


    private Connection conn;


    private boolean connect() {

        try {
            // Enregistrement du driver Oracle
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Etablissement de la connection
            conn = DriverManager.getConnection(CONN_URL,USER,PASSWD);

            // Desactivation de l'autocommit
            conn.setAutoCommit(false);


            // traitement d'exception
        } catch (SQLException e) {
            sqlerrorhandler(e);
            return true;
        }
        return false;
    }

    private void disconnect() {
        // Liberation des ressources et fermeture de la connexion...
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            sqlerrorhandler(e);
        }
    }


    void sqlerrorhandler(SQLException e){
        System.err.println("DataBase Fail");
        System.out.println("Affichage de la pile d'erreur");
        e.printStackTrace(System.err);
        System.out.println("Affichage du message d'erreur");
        System.out.println(e.getMessage());
        System.out.println("Affichage du code d'erreur");
        System.out.println(e.getErrorCode());
    }



    public ResultSet sendRequest(String sql) {
        if(connect()) return null;
        Statement stmt;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return rs;
    }


    public int sendUpdate(String sql) {
        if(connect()) return 0;
        Statement stmt;
        int rs = 0;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        disconnect();
        return rs;
    }



}
