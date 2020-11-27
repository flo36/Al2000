package main.java.BDD;

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
    public InteractionBaseOracle(String url, String usr, String psd){
        super();
        CONN_URL = url;
        USER = usr;
        PASSWD = psd;
    }

    private final String CONN_URL;
    private final String USER;
    private final String PASSWD;
    private boolean isConnected = false;


    private Connection conn;


    public boolean connect() {

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
        isConnected = true;
        return false;
    }

    public void disconnect() {
        // Liberation des ressources et fermeture de la connexion...
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            sqlerrorhandler(e);
        }
        isConnected = false;
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
        if(!isConnected) return null;
        Statement stmt;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public int sendUpdate(String sql) {
        if(!isConnected) return 0;
        Statement stmt;
        int rs = 0;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return rs;
    }



}
