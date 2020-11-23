package cinema;

import java.util.Date;

public class Film {

    private String titre;
    private Date annee;
    private Genre[] genres;
    private int agemini;
    private String resume;
    private int duree;
    private Celebrite[] realisateurs;
    private Celebrite[] acteurs;

    public Film(String titre, Date annee, Genre[] genres, int agemini, String resume, int duree, Celebrite[] realisateurs, Celebrite[] acteurs){
        this.titre = titre;
        this.annee = annee;
        this.genres = genres;
        this.agemini = agemini;
        this.resume = resume;
        this.duree = duree;
        this.realisateurs = realisateurs;
        this.acteurs = acteurs;
    }

    public String getTitre(){
        return this.titre;
    }

    public Date getAnnee(){
        return this.annee;
    }

    public Genre getGenre(){
        return this.genres;
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

    public Celebrite[] getRealisateurs(){
        return this.realisateurs;
    }

    public Celebrite[] getActeurs(){
        return this.acteurs;
    }

}
