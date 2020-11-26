package BDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Parser {

    public static ArrayList<String> bddToStringArrayFilm(ResultSet rs,ArrayList<String> rs2 ){
        ArrayList<String> S= new ArrayList<>();

        //resultset ici
        S.addAll(resultSetToArray(rs));

        S.addAll(rs2);

        return S;
    }


    static ArrayList<String> resultSetToArray(ResultSet rs) {
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
