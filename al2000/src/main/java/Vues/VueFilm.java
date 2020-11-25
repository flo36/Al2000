package Vues;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VueFilm extends JPanel {
	
	public VueFilm(String nom_film){
		ImageIcon image = new ImageIcon("Mettre l'image");
		JLabel poster = new JLabel(image);
		JLabel titre = new JLabel(nom_film);
		JLabel rea = new JLabel();
		JLabel genre = new JLabel("Genre :");
		JLabel cast = new JLabel("cast");
		JTextArea resume = new JTextArea();
		resume.setEditable(false);
		JButton louer = new JButton("Emprunter");
		
		
		this.add(poster);
		this.add(titre);
		this.add(rea);
		this.add(genre);
		this.add(cast);
		this.add(resume);
		this.add(louer);
	}
}
