package Vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.Abonne;
import Donnee.ALMediator;


public class VueUser extends JFrame{
	
	private static GestionnaireDeVues gestion = new GestionnaireDeVues();
	private ALMediator al = new ALMediator();
	
	public VueUser(){
		
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel accueil = new JPanel();
		accueil.setLayout(null);
		
		/////////////////////////Element de la vue///////////////////////////
		JLabel titre = new JLabel("Bonjour !");
		JButton retour = new JButton("Retour");
		JButton help = new JButton("?");
		JButton rendre = new JButton("Rendre DVD");
		JButton inscription = new JButton("S'abonner");
		JTextField recherche = new JTextField("Rechercher un film");
		JButton rechercher = new JButton("Recherche");
		JButton films = new JButton("Voir films");
		JLabel rep_help = new JLabel("En cas de problème veuillez demander au gérant du store");
		rep_help.setVisible(false);
		
		//////////////////////////////Action des boutons/////////////////////:
		
		//Retourne a la page accueil
		retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gestion.vueUser.setVisible(false);
				gestion.vueAbonne.setVisible(true);
			}
			
		});
		
		//help : Affiche un message en bas de la fenetre
		help.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rep_help.setBounds(10, 550, rep_help.getPreferredSize().width, rep_help.getPreferredSize().height);
				rep_help.setForeground(Color.BLUE);
				rep_help.setVisible(true);
				accueil.revalidate();		
			}
					
		});
		
		rechercher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String film = recherche.getText();
				System.out.println("Le film a chercher est :"+film);
			}
			
		});
		
		//Afficher tout les films
		films.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gestion.vueAbonne.setVisible(false);
				gestion.vueEnsembleFilm.setVisible(true);
			}
					
		});
		
		inscription.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//
			}
			
		});
		
		//Rendre un film
		rendre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				al.rendreUnFilm();
			}
			
		});
		
		///////////////Ajout des elements////////////////////////////:::
		accueil.add(titre);
		accueil.add(retour);
		accueil.add(help);
		accueil.add(recherche);
		accueil.add(inscription);
		accueil.add(rendre);
		accueil.add(rechercher);
		accueil.add(rep_help);
		accueil.add(films);
		
		////////////////////Placement des Elements//////////////////////
		//Potentiellement a changer
			Dimension d = new Dimension(titre.getPreferredSize());
			titre.setBounds(300, 10, d.width, d.height);
			d.setSize(retour.getPreferredSize());
			retour.setBounds(10, 10, d.width, d.height);
			d.setSize(help.getPreferredSize());
			help.setBounds(740, 10, d.width, d.height);
			d.setSize(recherche.getPreferredSize());
			recherche.setBounds(200, 150, d.width+200, d.height);
			rechercher.setBounds(200+d.width+210, 150, d.width, d.height);
			d.setSize(films.getPreferredSize());
			films.setBounds(300, 200, d.width, d.height);
			d.setSize(inscription.getPreferredSize());
			inscription.setBounds(300, 250, d.width, d.height);
			d.setSize(rendre.getPreferredSize());
			rendre.setBounds(300, 300, d.width, d.height);
		
		//////////////////////////:Dimension de la fenetre////////////////////////////
		Dimension d_accueil = new Dimension(800, 600);
		this.setMinimumSize(d_accueil);

		this.add(accueil);
		pack();
	}
	
	public void createPanelAbonnement() {
		Dimension d = new Dimension(100, 50);
		//Nom
		JLabel l_nom = new JLabel("Nom : ");
		JTextField t_nom = new JTextField();
		t_nom.setPreferredSize(d);
		//Prenom
		JLabel l_prenom = new JLabel("Prenom : ");
		JTextField t_prenom = new JTextField();
		t_prenom.setPreferredSize(d);
		//Email
		JLabel l_email = new JLabel("Email : ");
		JTextField t_email = new JTextField();
		t_email.setPreferredSize(d);
		//Age 
		JLabel l_age = new JLabel("Age : ");
		JTextField t_age = new JTextField();
		t_age.setPreferredSize(d);
		//Carte bleu
		JLabel l_carte = new JLabel("Numero de carte : ");
		JTextField t_carte = new JTextField();
		t_carte.setPreferredSize(d);
		//Ajouter et annuler
		JButton ajouter = new JButton("Ajouter");
		JButton annuler = new JButton("Annuler");
		
		//Action des boutons
		ajouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Abonne abonne = new Abonne(0, t_nom.getText(), t_prenom.getText(), 0, t_email.getText());
			}
			
		});
		
		annuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//
			}
			
		});
		//Ajout des elements
	}
	
	
	
}
