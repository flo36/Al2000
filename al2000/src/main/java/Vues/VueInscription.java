package Vues;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.Abonne;

public class VueInscription extends JFrame{
	

	private static final long serialVersionUID = 1L;
	
	private static GestionnaireDeVues gestion = new GestionnaireDeVues();
	
	public VueInscription() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel abonnement = new JPanel();
		abonnement.setLayout(null);
		
		Dimension d = new Dimension(150, 20);
		
		//Nom
		JLabel l_nom = new JLabel("Nom : ");
		JTextField t_nom = new JTextField();
		l_nom.setBounds(100, 50, d.width, d.height);
		t_nom.setBounds(110+d.width, 50, d.width, d.height);
		
		//Prenom
		JLabel l_prenom = new JLabel("Prenom : ");
		JTextField t_prenom = new JTextField();
		l_prenom.setBounds(100, 100, d.width, d.height);
		t_prenom.setBounds(110+d.width, 100, d.width, d.height);
		
		//Email
		JLabel l_email = new JLabel("Email : ");
		JTextField t_email = new JTextField();
		l_email.setBounds(100, 150, d.width, d.height);
		t_email.setBounds(110+d.width, 150, d.width, d.height);
		
		//Age 
		JLabel l_age = new JLabel("Age : ");
		JTextField t_age = new JTextField();
		l_age.setBounds(100, 200, d.width, d.height);
		t_age.setBounds(110+d.width, 200, d.width, d.height);
		
		//Carte bleu
		JLabel l_carte = new JLabel("Numero de carte : ");
		JTextField t_carte = new JTextField();
		l_carte.setBounds(100, 250, d.width, d.height);
		t_carte.setBounds(110+d.width, 250, d.width, d.height);
		
		//Ajouter et annuler
		JButton ajouter = new JButton("Ajouter");
		JButton annuler = new JButton("Annuler");
		d.setSize(annuler.getPreferredSize());
		ajouter.setBounds(250, 350, d.width, d.height);
		annuler.setBounds(450, 350, d.width, d.height);
		
		//Action des boutons
		ajouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Abonne abonne = new Abonne(0, t_nom.getText(), t_prenom.getText(), 0, t_email.getText());
				abonne.enregistre_Abonne();
				gestion.vueInscription.setVisible(false);
				gestion.vueAbonne.setVisible(true);
			}
			
		});
		
		annuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gestion.vueInscription.setVisible(false);
				gestion.vueUser.setVisible(true);
			}
			
		});
		
		//Ajout des elements
		abonnement.add(l_prenom);
		abonnement.add(t_prenom);
		abonnement.add(l_nom);
		abonnement.add(t_nom);
		abonnement.add(l_age);
		abonnement.add(t_age);
		abonnement.add(l_email);
		abonnement.add(t_email);
		abonnement.add(l_carte);
		abonnement.add(t_carte);
		abonnement.add(ajouter);
		abonnement.add(annuler);
		
		//////////////////////////:Dimension de la fenetre////////////////////////////
		Dimension d_accueil = new Dimension(800, 600);
		this.setMinimumSize(d_accueil);

		this.add(abonnement);
		pack();
		
	}

}
