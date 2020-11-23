package cinema;

public abstract class Celebrite {
    
    private int id_acteur;
    private String nom;
    private String courte_bio;
    private String[] filmographie;

    public Celebrite(int id_acteur, String nom, String courte_bio, String[] filmographie){
        this.id_acteur = id_acteur;
        this.nom = nom;
        this.courte_bio = courte_bio;
        this.filmographie = filmographie;
    }

    public int getId(){
        return this.id_acteur;
    }

    public String getNom(){
        return this.nom;
    }

    public String getBio(){
        return this.courte_bio;
    }

    public String[] getFilmographie(){
        return this.filmographie;
    }
    
}
