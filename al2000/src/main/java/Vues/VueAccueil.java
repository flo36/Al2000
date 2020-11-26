package Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class VueAccueil extends JFrame{

	private static GestionnaireDeVues gestion = new GestionnaireDeVues();
	
	public VueAccueil(){
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel accueil = new JPanel();
		accueil.setLayout(null);
		

		////////////////////Elements de l'accueil///////////////////////////////////
		JLabel titre = new JLabel("Bienvenue dans notre AL2000 !");
		JButton insersion_abonne = new JButton("Cliquez pour simuler l'insertion d'une carte abonne");
		JButton insertion = new JButton("Cliquez pour simuler une insertion");
		JButton maintenance = new JButton("Maintenance");
		JLabel debut = new JLabel("Veuillez inserer votre carte pour débuter");
		JButton help = new JButton("?");
		JLabel rep_help = new JLabel("En cas de problème veuillez demander au gérant du store");
		rep_help.setVisible(false);

		//////////////////////////Action des Boutons/////////////////////////////////
		
		//Maintenance : Afficher un menu popup pour entrer le mot de passe avant d'afficher la vue maintenance
		maintenance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//Creation d'un popup pour rentrer le mot de passe
				JPopupMenu maintenance = new JPopupMenu();
				//Creation des elements
				JTextField mdp = new JTextField();
				mdp.setSize(150, 20);
				JButton valider = new JButton("Valider");
				JButton annuler = new JButton("Annuler");
				//Action du bouton valider : Verifie que le mot de passe est correcte, si oui affiche la vue maintenance, si non affiche un message d'erreur
				valider.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String password = mdp.getText();
						if (password.equals("oui")){//A changer plus tard
							gestion.vueAccueil.setVisible(false);
							maintenance.setVisible(false);;
							gestion.vueMaintenance.setVisible(true);
						}else {
							System.out.println("NOPe");//A changer
						}
					}
					
				});
				//Action du bouton Annuler : cache le popup
				annuler.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						maintenance.setVisible(false);
					}
					
				});
				
				//ajout des ELements
				maintenance.add(new JLabel("Mot de passe :"));
				maintenance.add(mdp);
				maintenance.add(valider);
				maintenance.add(annuler);
				//Montrer le menu
				maintenance.show(gestion.vueAccueil, 10, 10);
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
		
		//inserion_abonne : affiche la vueAbonne
		insersion_abonne.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gestion.vueAccueil.setVisible(false);
				gestion.vueAbonne.setVisible(true);
			}
			
		});
		
		//inserion : affiche la vue User
		insertion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gestion.vueAccueil.setVisible(false);
				gestion.vueUser.setVisible(true);
			}	
		});
		
		//////////////////////////Ajout des elements//////////////////////////////
		accueil.add(titre);
		accueil.add(maintenance);
		accueil.add(help);
		accueil.add(debut);
		accueil.add(insersion_abonne);
		accueil.add(insertion);
		accueil.add(rep_help);
		
		/////////////////////////Positionnnement des elements/////////////////////////
		//Potentiellement a changer
		Dimension d = new Dimension(titre.getPreferredSize());
		titre.setBounds(300, 10, d.width, d.height);
		d.setSize(maintenance.getPreferredSize());
		maintenance.setBounds(10, 10, d.width, d.height);
		d.setSize(help.getPreferredSize());
		help.setBounds(740, 10, d.width, d.height);
		d.setSize(debut.getPreferredSize());
		debut.setBounds(300, 250, d.width, d.height);
		d.setSize(insersion_abonne.getPreferredSize());
		insersion_abonne.setBounds(250, 300, d.width, d.height);
		d.setSize(insertion.getPreferredSize());
		insertion.setBounds(300, 350, d.width, d.height);
		
		//////////////////////////:Dimension de la fenetre////////////////////////////
		Dimension d_accueil = new Dimension(800, 600);
		this.setMinimumSize(d_accueil);
		
		this.add(accueil);
		pack();
	}
	
	public static void main(String[] argv) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				gestion.vueAccueil.setVisible(true); 
			}
		});
	}
}
