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
        final int PARSESIZE = 4;
    /**
     * Parsing :
     * #id
     * nom du film
     * realisateur
     * disponibilité : true ou false
     *
     */

    public ArrayList<String> sendRequest(String sql) {
        if (sql.equals("SELECT nomF FROM LesFilmsDisponibles"))
            return getAllFilmsAvailable();

        String[] sqls = sql.trim().substring(47).trim().split(" ");
        if (sqls[0].trim().equals("SELECT")){
            return getFilm(sqls[1],sqls[2]);

        }


        return null;

    }

    public int sendUpdate(String notsql) {
        String[] sqls = notsql.trim().split(" ");
        switch (sqls[0]){
            case "UPDATE":
                return changeAvailability(parseInt(sqls[1]));
            case "INSERT":
                return addFilm(sqls[1], sqls[2]);
            case "DELETE":
                return removeFilm(parseInt(sqls[1]));
            default:
                return 0;

        }
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

    private ArrayList<String> getFilm(String name, String real){
        ArrayList<String> S = readContentFromFile();
        int i = 0;

        while (i < S.size()){
            if(S.get(i+1).equals(name) && S.get(i+2).equals(real)){
               i+=4;
            }else{
                for (int k = 0; k < PARSESIZE; k++)
                    S.remove(i);
            }
        }

        return S;
    }




    private int changeAvailability(int id){
        ArrayList<String> S = readContentFromFile();

        int pos = S.indexOf("#"+id) + PARSESIZE -1;
        boolean available = !Boolean.parseBoolean(S.get(pos));
        S.set(pos,Boolean.toString(available));

        if(writeContentInFile(S))
            return 1;
        else
            return 0;
    }

    private int addFilm(String nom, String real){
        ArrayList<String> S = readContentFromFile();

        S.add("#"+parseInt(S.get(S.size()-3).substring(1)+1));
        S.add(nom);
        S.add(real);
        S.add("true");

        if(writeContentInFile(S))
            return PARSESIZE;
        else
            return 0;
    }

    private int removeFilm(int id){
        ArrayList<String> S = readContentFromFile();

        int pos = S.indexOf("#"+id);
        for (int i = 0; i < PARSESIZE; i++)
            S.remove(pos);

        for (int i = pos; i < S.size(); i++) {
            if(S.get(i).charAt(0) == '#')
                S.set(i,"#" + (parseInt(S.get(i).substring(1))-1));
        }

        if(writeContentInFile(S))
            return -PARSESIZE;
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
