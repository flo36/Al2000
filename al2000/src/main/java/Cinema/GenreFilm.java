package main.java.Cinema;

public class GenreFilm {
    private Genre g;
    private Film f;

    public GenreFilm(Genre g, Film f){
        this.g = g;
        this.f = f;
    }

    public Genre getGenre(){
        return this.g;
    }

    public Film getFilm(){
        return this.f;
    }
}
