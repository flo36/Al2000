package Cinema;

public class Filmographie {

    private Celebrite celebrite; 
    private Film film;

    public Filmographie(Celebrite celebrite, Film film){
        this.celebrite = celebrite;
        this.film = film;
    }

    public Celebrite getCelebrite(){
        return this.celebrite;
    }

    public Film getFilm(){
        return this.film;
    }
}