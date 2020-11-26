package src.main.java.Cinema;

public abstract class Celebrite {
    
    private int id_acteur;
    private String nom;
    private String courte_bio;

    public Celebrite(int id_acteur, String nom, String courte_bio){
        this.id_acteur = id_acteur;
        this.nom = nom;
        this.courte_bio = courte_bio;
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
    
}
