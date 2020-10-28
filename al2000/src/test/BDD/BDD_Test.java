package BDD;

import org.junit.jupiter.api.Test;

class BDD_Test {

        @Test
        public void BDDconnect() {
            ClientOracle.instance().sendRequest(";");
            System.out.println("BDD Connection OK");
        }
}