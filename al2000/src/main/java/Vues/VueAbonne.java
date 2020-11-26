/**A Mettre a jour sur github**/

package src.main.java.Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.main.java.Cinema.Genre;
import src.main.java.Client.Abonne;

public class VueAbonne extends JFrame{
	
	private static GestionnaireDeVues gestion = new GestionnaireDeVues();
	private Abonne abo = new Abonne(0, "Test", "Tester", 3, "mail");
	
	public VueAbonne(){
		
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BorderLayout blayout = new BorderLayout();
		JPanel ensemble = new JPanel(blayout);
		JPanel top_ecran = new JPanel();
		JPanel accueil = new JPanel();
		accueil.setLayout(null);
		//top_ecran.setLayout(null);
		
		/////////////////////////Element de la vue///////////////////////////
		JLabel titre = new JLabel("Bonjour "+abo.getPrenom()+" !");
		JButton retour = new JButton("Retour");
		JButton help = new JButton("?");
		JButton rendre = new JButton("Rendre DVD");
		JButton films = new JButton("Voir Films");
		//A changer
		JComboBox restriction = new JComboBox();
		restriction.addItem(Genre.ACTION);
		restriction.addItem(Genre.ADVENTURE);
		restriction.addItem(Genre.COMEDY);
		restriction.addItem(Genre.CRIME);
		restriction.addItem(Genre.DRAMA);
		//Fin du a changer
		JButton add_restriction = new JButton("Ajouter Restrictions");
		JButton suppr_restriction = new JButton("Supprimer Restrictions");
		JTextField recherche = new JTextField("Rechercher un film");
		JButton recherche_al2000 = new JButton("in AL2000");
		JButton recherche_cyber = new JButton("in Store");//Dans CyberVideo
		JTextField t_montant = new JTextField("tapez la somme que vous souhaitez ajouter a votre carte");
		JButton recharger_carte = new JButton("Recharger Carte");
		JButton historique = new JButton("Consulter Historique");
		JLabel rep_help = new JLabel("En cas de problème veuillez demander au gérant du store");
		rep_help.setVisible(false);
		
		//////////////////////////////Action des boutons/////////////////////:
		
		//retourner à la page précedente
		retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gestion.vueAbonne.setVisible(false);
				gestion.vueAccueil.setVisible(true);
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
		
		//Le film est recherché parmis les films de la machine 
		recherche_al2000.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String film = recherche.getText();
				System.out.println("Le film a chercher est :"+film);
			}
			
		});
		
		//Le film est recherché parmis les films de CyberVideo
		recherche_cyber.addActionListener(new ActionListener() {

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
		
		//Recharger la carte
		recharger_carte.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Le montant ajouter et de "+t_montant.getText());
			}
			
		});
		
		//Voir l'historique 
		historique.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//
			}
			
		});
		
		//Ajouter une restriction
		add_restriction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				abo.ajoutRestriction((Genre) restriction.getSelectedItem());
				System.out.println("Les restrictions sont :"+abo.getRestrictions());
			}
			
		});
		
		//Supprimer une restriction
		suppr_restriction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				abo.enleveRestriction((Genre) restriction.getSelectedItem());
				System.out.println("Les restrictions sont :"+abo.getRestrictions());
			}
			
		});
		
		///////////////Ajout des elements////////////////////////////:::
		top_ecran.add(retour);
		top_ecran.add(titre);
		top_ecran.add(help);
		accueil.add(recherche);
		accueil.add(rendre);
		accueil.add(recherche_al2000);
		accueil.add(recherche_cyber);
		accueil.add(films);
		accueil.add(recharger_carte);
		accueil.add(t_montant);
		accueil.add(historique);
		accueil.add(rep_help);
		accueil.add(restriction);
		accueil.add(add_restriction);
		accueil.add(suppr_restriction);
		
		////////////////////Placement des Elements//////////////////////
		//Potentiellement a changer et a passer en Dynamique : pas forcement utile car taille de l'ecran ne change pas
		//Top_ecran : titre/retour/help
		Dimension d = new Dimension(titre.getPreferredSize());
		titre.setBounds(300, 10, d.width, d.height);
		d.setSize(retour.getPreferredSize());
		retour.setBounds(10, 10, d.width, d.height);
		d.setSize(help.getPreferredSize());
		help.setBounds(740, 10, d.width, d.height);
		//Recherche 
		d.setSize(recherche.getPreferredSize());
		recherche.setBounds(150, 150, d.width+200, d.height);
		recherche_al2000.setBounds(150+d.width+210, 150, d.width, d.height);
		recherche_cyber.setBounds(150+d.width+350, 150, d.width, d.height);
		//Voir film
		d.setSize(films.getPreferredSize());
		films.setBounds(300, 200, d.width, d.height);
		//Rendre film
		d.setSize(rendre.getPreferredSize());
		rendre.setBounds(300, 250, d.width, d.height);
		//Recharger carte
		d.setSize(t_montant.getPreferredSize());
		t_montant.setBounds(100, 300, d.width+10, d.height);
		d.setSize(recharger_carte.getPreferredSize());
		recharger_carte.setBounds(465, 300, d.width, d.height-5);
		//Historique
		d.setSize(historique.getPreferredSize());
		historique.setBounds(300, 350, d.width, d.height);
		//Restrictions
		d.setSize(restriction.getPreferredSize());
		restriction.setBounds(150, 400, d.width, d.height);
		add_restriction.setBounds(155+d.width, 400, d.width+80, d.height);
		suppr_restriction.setBounds(350+d.width, 400, d.width+100, d.height);
		
		//////////////////////////:Dimension de la fenetre////////////////////////////
		Dimension d_accueil = new Dimension(800, 600);
		this.setMinimumSize(d_accueil);

		ensemble.add(top_ecran, blayout.NORTH);
		ensemble.add(accueil, blayout.CENTER);
		this.add(ensemble);
		pack();
	}
	
}
