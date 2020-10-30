package BDD;

import Model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RequestCyberVideo {

    public static String[] getFilmList() {
        ResultSet rs = ClientOracle.instance().sendRequest("SELECT nomF FROM LesFilms");
        return (String[]) resultSetToArray1(rs).toArray();
    }

    public static Film getFilm(String name){
        ResultSet rs = ClientOracle.instance().sendRequest("SELECT * from LesFilms where nomF = '"+ name +"'");
        return new Film();
        //A changer il faut construire le film
    }


    private static ArrayList<String> resultSetToArray1(ResultSet rs){
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
    private static ArrayList<String>[] resultSetToArray2(ResultSet rs){
        ArrayList<String>[] l = new ArrayList[2];

        while (true) {
            try {
                if (!rs.next()) break;
                l[0].add(rs.getString(1));
                l[1].add(rs.getString(2));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return l;
    }
}
