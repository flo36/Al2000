package src.main.java.Vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueUser extends JFrame{
	
	private static GestionnaireDeVues gestion = new GestionnaireDeVues();
	
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
		
		///////////////Ajout des elements////////////////////////////:::
		accueil.add(titre);
		accueil.add(retour);
		accueil.add(help);
		accueil.add(recherche);
		accueil.add(inscription);
		accueil.add(rendre);
		accueil.add(rechercher);
		accueil.add(rep_help);
		
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
			d.setSize(inscription.getPreferredSize());
			inscription.setBounds(300, 200, d.width, d.height);
			d.setSize(rendre.getPreferredSize());
			rendre.setBounds(300, 250, d.width, d.height);
		
		//////////////////////////:Dimension de la fenetre////////////////////////////
		Dimension d_accueil = new Dimension(800, 600);
		this.setMinimumSize(d_accueil);

		this.add(accueil);
		pack();
	}
	
}
