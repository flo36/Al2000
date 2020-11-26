package cinema;

public class Dvd {
    
    private int etat;
    private boolean sous_titres;

    public Dvd(int etat, boolean sous_titres){
        this.etat = etat;
        this.sous_titres = sous_titres;
    }

    public int getEtat(){
        return this.etat;
    }

    public boolean getSousTitres(){
        return this.sous_titres;
    }
    
}
