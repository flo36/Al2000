package BDD;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestCyberVideoTest {

    @Test
    void getFilmList() {
        BDD.RequestCyberVideo.getFilmList();
    }

    @Test
    void getFilm() {
        BDD.RequestCyberVideo.getFilm("test");
    }
}