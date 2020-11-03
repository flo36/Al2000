package BDD;

import java.io.*;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class InteractionBaseLocale {
        InteractionBaseLocale(){
        super();
    }
    final String PATH = "BDD/filmlocal.bdd";

    /**
     * Parsing :
     * #id
     * nom du film
     * disponibilité : true ou false
     *
     */

    public ArrayList<String> sendRequest(String sql) {
        String[] sqls = sql.trim().split(" ");
        if (sql.equals("SELECT nomF FROM LesFilmsDisponibles"))
            return getAllFilmsAvailable();
        else if (sqls.length>=6 && sql.trim().substring(0,39).equals("SELECT * FROM LesFilmsDisponibles WHERE"))
            return getFilm(sqls[7]);
        else
            return null;

    }

    public int sendUpdate(String sql) {
        String[] sqls = sql.trim().split(" ");
        if (sqls.length>=8 && sql.trim().substring(0,59).equals("UPDATE LesFilmsDisponibles SET available = !available WHERE"))
            return changeAvailability(parseInt(sqls[9]));
        else if(sqls.length>=5 && sql.trim().substring(0,38).equals("INSERT INTO LesFilmsDisponibles VALUES"))
            return addFilm(sqls[4].substring(2,sqls[4].length()-2));
        else if(sqls.length>=5 && sql.trim().substring(0,37).equals("DELETE FROM LesFilmsDisponibles WHERE"))
            return removeFilm(parseInt(sqls[6]));
        else
            return 0;
    }


    private ArrayList<String> getAllFilmsAvailable(){
        ArrayList<String> S = readContentFromFile();

        int i = 0;

        while (i < S.size()){
            if(S.get(i).charAt(0) == '#')
                S.remove(i);
            else if(S.get(i).equals("true") || S.get(i).equals("false"))
                S.remove(i);
            else
                i++;
        }

        return S;
    }

    private ArrayList<String> getFilm(String name){
        ArrayList<String> S = readContentFromFile();
        int i = 0;

        while (i < S.size()){
            if(S.get(i+1).equals(name)){
               i+=3;
            }else{
                S.remove(i);
                S.remove(i);
                S.remove(i);
            }
        }

        return S;
    }




    private int changeAvailability(int id){
        ArrayList<String> S = readContentFromFile();

        int pos = S.indexOf("#"+id);
        boolean available = !Boolean.parseBoolean(S.get(pos + 2));
        S.set(pos+2,Boolean.toString(available));

        if(writeContentInFile(S))
            return 1;
        else
            return 0;
    }

    private int addFilm(String nom){
        ArrayList<String> S = readContentFromFile();

        S.add("#"+parseInt(S.get(S.size()-3).substring(1)+1));
        S.add(nom);
        S.add("true");

        if(writeContentInFile(S))
            return 3;
        else
            return 0;
    }

    private int removeFilm(int id){
        ArrayList<String> S = readContentFromFile();

        int pos = S.indexOf("#"+id);
        S.remove(pos);
        S.remove(pos);
        S.remove(pos);

        for (int i = pos; i < S.size(); i++) {
            if(S.get(i).charAt(0) == '#')
                S.set(i,"#" + (parseInt(S.get(i).substring(1))-1));
        }

        if(writeContentInFile(S))
            return -3;
        else
            return 0;
    }







    private ArrayList<String> readContentFromFile(){
        ArrayList<String> S = new ArrayList<>();
        String temp;
        try {
            BufferedReader in_stream = new BufferedReader(new FileReader(resourcesPath() +PATH));
            while ((temp = in_stream.readLine()) != null) {
                S.add(temp);
            }
            in_stream.close();

        } catch (IOException e) {
            e.printStackTrace();
            S.clear();
            S.add("Erreur de lecture de la base locale");
            return S;
        }
        return S;
    }

    private boolean writeContentInFile(ArrayList<String> content){
        try {
            BufferedWriter out_stream = new BufferedWriter(new FileWriter(resourcesPath() + PATH));
            for (String s : content) {
                out_stream.write(s);
                out_stream.write('\n');
            }
            out_stream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String resourcesPath(){
        return Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("")).getPath();
    }
}
