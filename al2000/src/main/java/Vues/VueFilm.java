package main.java.Vues;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.Cinema.Film;
import main.java.Donnee.ALMediator;



public class VueFilm extends JPanel {
	
	private ALMediator al = new ALMediator();
	
	public VueFilm(Film film){
		this.setLayout(null);
		//this.setLayout(new GridLayout());
		//Genre[] g = film.getGenre();
		Dimension d = new Dimension();
		/**Creation des element et ajout de ceux ci au panel**/
		//Poster
		ImageIcon image = new ImageIcon("Mettre l'image");
		JLabel poster = new JLabel(image);
		d = poster.getPreferredSize();
		poster.setBounds(20, 20, d.width, d.height);
		this.add(poster);
		//Titre
		JLabel titre = new JLabel(film.getTitre());
		d = titre.getPreferredSize();
		titre.setBounds(100, 20, d.width, d.height);
		this.add(titre);
		//Realisateurs
		JLabel rea = new JLabel("Les realisateurs seront ici");
		d = rea.getPreferredSize();
		rea.setBounds(100, 60, d.width, d.height);
		this.add(rea);
		//Genre
		/*JLabel genre = new JLabel("Genre :");
		d = genre.getPreferredSize();
		genre.setBounds(100, 90, d.width, d.height);
		this.add(genre);
		for(int i = 0; i<g.length; i++) { 
			JLabel l = new JLabel(g[i].toString());
			d = l.getPreferredSize();
			l.setBounds(110+(i*100), 110, d.width, d.height);
			this.add(l);
		}*/
		//Acteurs
		JLabel cast = new JLabel("Les Acteurs seront ici");
		d = cast.getPreferredSize();
		cast.setBounds(100, 150, d.width, d.height);
		this.add(cast);
		//Resumé
		JLabel resume = new JLabel("Resume : "+film.getResume());
		d = resume.getPreferredSize();
		resume.setBounds(100, 200, d.width, d.height);
		this.add(resume);
		//Age minimum
		JLabel age = new JLabel("Age minimum :"+ film.getAgeMini());
		d = age.getPreferredSize();
		age.setBounds(100, 250, d.width, d.height);
		this.add(age);
		//Duree
		JLabel duree = new JLabel("Durée : "+ film.getDuree());
		d = duree.getPreferredSize();
		duree.setBounds(100, 300, d.width, d.height);
		this.add(duree);
		//Bouton
		JButton louer = new JButton("Emprunter");
		d = louer.getPreferredSize();
		louer.setBounds(200, 400, d.width, d.height);
		
		//Action de louer
		louer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int res = al.louerFilms(film);
				//A changer
				if(res == 0) {
					System.out.println("Le film a bien ete loué");
				}else {
					System.out.println("Rate");
				}
				
				
			}
			
		});

		this.add(louer);
		
		
	}
}
