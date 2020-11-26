import javax.swing.SwingUtilities;

import Vues.*;

/*
    Point d'entrée du programme

*/

public class Al2000 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VueUser().setVisible(true);
			}
		});
    }
}