package main.java.Cinema;

public class Dvd {
    
    private int etat;
    private boolean sous_titres;
    private Film film;

    public Dvd(int etat, boolean sous_titres, Film film){
        this.etat = etat;
        this.sous_titres = sous_titres;
        this.film = film;
    }
    
    public Dvd(Film f)
    {
    	this.film = f;
    }

    public int getEtat(){
        return this.etat;
    }

    public boolean getSousTitres(){
        return this.sous_titres;
    }

    public Film getFilm(){
        return this.film;
    }
    
}
