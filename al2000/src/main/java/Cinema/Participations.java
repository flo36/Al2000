package Cinema;

public class Participations {

    private Celebrite celebrite; 
    private Film film;

    public Participations(Celebrite celebrite, Film film){
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