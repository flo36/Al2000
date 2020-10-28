package BDD;

import java.sql.*;

/**
 Interface :
 sendRequest(String requeteSQL)
 -> Envoie la requete et retourne un resultset
 sendUpdate(String requeteSQL)
 -> Envoie la mise à jour et retourne le nombre de changements
 */

public class ClientOracle {
    // Pattern Singleton
    static volatile ClientOracle instance = null;
    public static ClientOracle instance(){
        if(instance == null)
            instance = new ClientOracle();
        return instance;
    }
    private ClientOracle(){
        super();
    }


    final String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
    final String USER = "test";
    final String PASSWD = "pw";

    Connection conn;


    private void connect() {

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
        }
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


    public int envoyerUpdate(String sql) {
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
