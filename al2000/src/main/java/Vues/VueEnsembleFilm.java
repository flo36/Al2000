package Vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import Cinema.Film;
import Cinema.Genre;
import Donnee.ALMediator;

public class VueEnsembleFilm extends JFrame{
	
	private static GestionnaireDeVues gestion = new GestionnaireDeVues();
	private ALMediator al = new ALMediator();
	private ArrayList<Film> resultat = new ArrayList<Film>();
	
	public VueEnsembleFilm(){
		
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Panel gauche
		JPanel gauche = new JPanel(); 
		gauche.setLayout(null);

		//Panel droit
		JPanel droit = new JPanel();
		droit.setLayout(null);
		JPanel liste_film = new JPanel();
		liste_film.setBounds(10, 10, 550, 550);
		
		droit.add(liste_film);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,gauche, droit);
		splitPane.setDividerLocation(180);
		
		/***************************Panel Gauche***********************************/
		//Element du panel
		JButton retour = new JButton("Retour");
		JButton help = new JButton("?");
		JLabel rep_help = new JLabel("En cas de problème veuillez demander au gérant du store");
		rep_help.setVisible(false);
		
		JTextField nom_recherche = new JTextField("Rechercher un film par ");
		JButton recherche = new JButton("Rechercher");
		JComboBox choix_recherche = new JComboBox();
		choix_recherche.addItem("Titre");
		choix_recherche.addItem("Genre");
		choix_recherche.addItem("Acteurs");
		choix_recherche.addItem("Realisateurs");
		
		//Ajouter tout les rechercher par genre, acteur, realisateur....
		
		gauche.add(retour);
		gauche.add(help);
		gauche.add(nom_recherche);
		gauche.add(choix_recherche);
		gauche.add(recherche);
		gauche.add(rep_help);
		
		////////////////////////////////Actions des boutons//////////////////////////////////////////////
		//Retour : retourne a la vue accueil
		retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gestion.vueEnsembleFilm.setVisible(false);
				gestion.vueAccueil.setVisible(true);
			}
					
		});
		
		//help : affiche un message en base de l'ecran
		help.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rep_help.setBounds(10, 550, rep_help.getPreferredSize().width, rep_help.getPreferredSize().height);
				rep_help.setForeground(Color.BLUE);
				rep_help.setVisible(true);
				gauche.revalidate();		
			}
			
		});
		
		/**Creation du panel droit dans cette fonction**/
		//Recherche un film par titre, genre, acteur ou realisateur
		recherche.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				/**Test en attendant la BDD
				Date date = new Date();
				Genre[] genre = {Genre.ACTION, Genre.ADVENTURE};
				Film f = new Film("Tesr", date, 10,"un film banal", 180 );
				Film ff = new Film("essai", date, 12, "un film plus violent", 165);
				Film fff = new Film("encore", date, 6, "un film pour les petits", 113);
				resultat.add(f);
				resultat.add(ff);
				resultat.add(fff);
				Fin des tests en attendant la bdd**/
				
				//Choix de la recherche
				if(choix_recherche.getSelectedItem().equals("Titre")) {
					resultat = al.RechercheParTitre(nom_recherche.getText());
					System.out.println("Recherche par titre");
				}else if(choix_recherche.getSelectedItem().equals("Genre")) {
					resultat = al.RechercheParGenre(nom_recherche.getText());
					System.out.println("Recherche par Genre");
				}else if(choix_recherche.getSelectedItem().equals("Acteurs")) {
					resultat = al.RechercheParActeur(nom_recherche.getText());
					System.out.println("Recherche par acteurs");
				}else if(choix_recherche.getSelectedItem().equals("Realisateurs")){
					resultat = al.RechercheParRealisateurs(nom_recherche.getText());
					System.out.println("recherche par realisateur");
				}
				
				//Affichage du resultat de la recherche
				if(resultat.size() ==  1) {
					VueFilm film = new VueFilm(resultat.get(0));
					film.setBounds(10, 10, 550, 550);
					droit.add(film);
					liste_film.setVisible(false);
					film.setVisible(true);
				}else {
					for(int i = 0; i<resultat.size(); i++) {
						//On cree un bouton pour chaque film
						JButton b = new JButton(resultat.get(i).getTitre());
						int index = i;
						b.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								//On cree aussi une vue pour chaque film qui sera visible que si le bouton est clique
								VueFilm film = new VueFilm(resultat.get(index));
								film.setBounds(10, 10, 550, 550);
								droit.add(film);
								film.setVisible(true);
								liste_film.setVisible(false);
							}
							
						});
						liste_film.add(b);
						liste_film.setVisible(true);
						
						
					}
				}
				droit.revalidate();
				System.out.println("Revalidate");
				
				
				
			}
			
		});
		/**Fin de la creation du panel droit**/
		
		//Position des Elements
		Dimension d = new Dimension(retour.getPreferredSize());
		retour.setBounds(10, 10, d.width, d.height);
		d.setSize(help.getPreferredSize());
		help.setBounds(100, 10, d.width, d.height);
		d.setSize(nom_recherche.getPreferredSize());
		nom_recherche.setBounds(10, 50, d.width, d.height);
		choix_recherche.setBounds(10, 55+d.height, d.width, d.height);
		d.setSize(recherche.getPreferredSize());
		recherche.setBounds(10, 100, d.width, d.height);
		
		/******************************Fin Panel Gauche******************************/

		//Fenetre
		Dimension d_accueil = new Dimension(800, 600);
		this.setMinimumSize(d_accueil);

		this.add(splitPane);
		pack();
	}
}
