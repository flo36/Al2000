import BDD.ClientOracle;
import org.junit.jupiter.api.Test;

public class BDD_Test {

    @Test
    public void BDDconnect() {
        ClientOracle.instance().sendRequest(";");
        System.out.println("BDD Connection OK");
    }}
