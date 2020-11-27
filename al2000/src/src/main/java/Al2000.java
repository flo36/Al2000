package src.main.java;

import javax.swing.SwingUtilities;

import main.java.Vues.VueAccueil;

/*
    Point d'entrée du programme

*/

public class Al2000 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VueAccueil().setVisible(true);
			}
		});
    }
}