package Vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class VueEnsembleFilm extends JFrame{
	
	private static GestionnaireDeVues gestion = new GestionnaireDeVues();
	private boolean montrer_liste = true;
	
	public VueEnsembleFilm(){
		
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Ajout des Elements 
		JPanel gauche = new JPanel(); 
		gauche.setLayout(null);
		//Les panel droit contiendra soit le panel droit_liste_film, soit la vuefilm
		JScrollPane droit= new JScrollPane();
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
		
		//Recherche un film par titre, genre, acteur ou realisateur
		recherche.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(choix_recherche.getSelectedItem().equals("Titre")) {
					//Lancer la recherche par titre
					System.out.println("Recherche par titre");
				}else if(choix_recherche.getSelectedItem().equals("Genre")) {
					//Lancer la recherche par genre
					System.out.println("Recherche par Genre");
				}else if(choix_recherche.getSelectedItem().equals("Acteurs")) {
					//Lancer la recherche par acteurs
					System.out.println("Recherche par acteurs");
				}else if(choix_recherche.getSelectedItem().equals("Realisateurs")){
					//Lancer la recherche par realisateur
					System.out.println("recherche par realisateur");
				}
				
				
				
			}
			
		});
		
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
		
		/*******************************Panel Droit********************************/
		//droit ne peux pas etre un gridlayot avec un scrollpane
		//Faire un for et ajouter chaque film au panel droit
		
		
		/*****************************Fin Panel Droit*********************************/
		
		//Fenetre
		Dimension d_accueil = new Dimension(800, 600);
		this.setMinimumSize(d_accueil);

		this.add(splitPane);
		pack();
	}
}
