import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import BDD.InteractionBaseLocale;
import BDD.InteractionBaseOracle;
import Cinema.*;
class BaseTest {
    @Test
    void tTest() {
        InteractionBaseLocale bdd = new InteractionBaseLocale();
        System.out.println(bdd.sendRequest("SELECT * FROM LesFilms"));
        assertTrue(true);
    }
}
