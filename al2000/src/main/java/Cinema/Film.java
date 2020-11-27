package main.java.Cinema;

import java.util.Date;

public class Film {

    private String titre;
    private Date annee;
    private int agemini;
    private String resume;
    private int duree;

    public Film(String titre, Date annee, int agemini, String resume, int duree){
        this.titre = titre;
        this.annee = annee;
        this.agemini = agemini;
        this.resume = resume;
        this.duree = duree;
    }
    
    public Film(String titre, Date annee)
    {
    	this.titre = titre;
    	this.annee = annee;
    }

    public String getTitre(){
        return this.titre;
    }

    public Date getAnnee(){
        return this.annee;
    }

    public int getAgeMini(){
        return this.agemini;
    }

    public String getResume(){
        return this.resume;
    }

    public int getDuree(){
        return this.duree;
    }

    @Override
    public String toString(){
        return titre +" "+ annee +" "+ agemini +" "+ resume +" "+ duree;
    }
}
