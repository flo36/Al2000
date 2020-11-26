package BDD;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Parser {

    public static ArrayList<String> bddToStringArrayFilm(ResultSet rs,ArrayList<String> rs2 ){
        ArrayList<String> S= new ArrayList<>();

        //resultset ici
        S.add("resultset");

        S.addAll(rs2);

        return S;
    }

}
