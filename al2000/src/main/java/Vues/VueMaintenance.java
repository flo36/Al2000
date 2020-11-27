package main.java.Vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VueMaintenance extends JFrame{
	
	private static GestionnaireDeVues gestion = new GestionnaireDeVues();

	public VueMaintenance(){
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel accueil = new JPanel();
		accueil.setLayout(null);
		
		////////////////Element de la vue maintenance////////////////////////
		JLabel titre = new JLabel("Maintenance");
		JButton ajouter = new JButton("Ajouter film");
		JButton stat = new JButton("Statistiques");
		JButton modif = new JButton("Modifier l'écran d'accueil");
		JButton retour = new JButton("Retour");
		JButton help = new JButton("?");
		JLabel rep_help = new JLabel("En cas de problème veuillez demander au gérant du store");
		rep_help.setVisible(false);
		
		////////////////Action des boutons////////////////////////
		ajouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ajouter film");
				//Creation des Elements
				JPanel ajout_film = new JPanel();
				JLabel film = new JLabel("Entrer le titre du film que vous souhaitez ajouter : ");
				film.setBounds(150, 150, 250, 30);
				JTextField titre_film = new JTextField();
				titre_film.setSize(250, 30);
				JButton add= new JButton("Ajouter");
				JButton cancel = new JButton("Annuler");
				
				System.out.println(film.getText());
				
				//Ajout des Elements
				gestion.vueMaintenance.add(film);
				ajout_film.add(titre_film);
				ajout_film.add(add);
				ajout_film.add(cancel);
				gestion.vueMaintenance.add(ajout_film);
				ajout_film.setVisible(true);
				gestion.vueMaintenance.revalidate();
				
			}
			
		});
		
		stat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("voir statistiques");
			}
			
		});
		
		modif.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("modifier ecran d'accueil");
			}
			
		});
		
		
		//Retourne a la vue accueil
		retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gestion.vueMaintenance.setVisible(false);
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
		
		///////////////Ajout des elements////////////////////////////:::
		accueil.add(titre);
		accueil.add(ajouter);
		accueil.add(stat);
		accueil.add(modif);
		accueil.add(retour);
		accueil.add(help);
		accueil.add(rep_help);
		
		////////////////////Placement des Elements//////////////////////
		//Potentiellement a changer
			Dimension d = new Dimension(titre.getPreferredSize());
			titre.setBounds(300, 10, d.width, d.height);
			d.setSize(ajouter.getPreferredSize());
			ajouter.setBounds(100, 100, d.width, d.height);
			d.setSize(stat.getPreferredSize());
			stat.setBounds(250, 100, d.width, d.height);
			d.setSize(modif.getPreferredSize());
			modif.setBounds(400, 100, d.width, d.height);
			d.setSize(retour.getPreferredSize());
			retour.setBounds(10, 10, d.width, d.height);
			d.setSize(help.getPreferredSize());
			help.setBounds(740, 10, d.width, d.height);
			
				
		//////////////////////////:Dimension de la fenetre////////////////////////////
		Dimension d_accueil = new Dimension(800, 600);
		this.setMinimumSize(d_accueil);
		
		this.add(accueil);
		pack();
	}

}
