package Cinema;

public class Genre {
    private GenreDispos g;
    private Film f;

    public Genre(GenreDispos g, Film f){
        this.g = g;
        this.f = f;
    }

    public GenreDispos getGenre(){
        return this.g;
    }

    public Film getFilm(){
        return this.f;
    }
}
