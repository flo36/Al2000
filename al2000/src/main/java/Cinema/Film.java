package cinema;

import java.util.Date;

public class Film {

    private String titre;
    private Date annee;
    private Genre[] genres;
    private int agemini;
    private String resume;
    private int duree;

    public Film(String titre, Date annee, Genre[] genres, int agemini, String resume, int duree){
        this.titre = titre;
        this.annee = annee;
        this.genres = genres;
        this.agemini = agemini;
        this.resume = resume;
        this.duree = duree;
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

}
