package Donnee;

import java.util.ArrayList;
import java.util.Locale;

import BDD.InteractionBaseOracle;
import BDD.Requete;
import BDD.TransactionBanque;
import Cinema.Film;
import Client.Abonne;
import Client.Utilisateur;
import Controller.Controleur;
import Vues.VueAccueil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ALMediator {

	private Statistiques outils_stat;
	private Utilisateur user;
	private Controleur controleur;
	private VueAccueil acceuil;
	private Requete requete;
	private TransactionBanque t_banque;

	public ALMediator() {
		this.acceuil = new VueAccueil();
		this.outils_stat = new Statistiques();
		this.controleur = new Controleur();
		this.requete = new Requete();
		this.t_banque = new TransactionBanque();
		// on attendras l'insertion d'une carte pour instancier User.
	}

	public void IdentificationAcceuil(int debug) {
		// verifie le lecteur carte pour idientifier le client, String debug pour
		// contourner l'abscnece de carte)
		if (this.controleur.haveCarteAbo()) {
			ArrayList<String> res = requete.getUser(Integer.parseInt(this.controleur.lireCarteAbo()));
			this.user = new Abonne(Integer.parseInt(res.get(0)), res.get(1), res.get(2), Integer.parseInt(res.get(3)),
					res.get(4));
		} else if (debug != -1) {
			ArrayList<String> res = requete.getUser(debug);
			this.user = new Abonne(Integer.parseInt(res.get(0)), res.get(1), res.get(2), Integer.parseInt(res.get(3)),
					res.get(4));
		} else if (this.controleur.haveCarteBleu()) {
			ArrayList<String> res = requete.getUser(Integer.parseInt(this.controleur.lireCarteBleu()));
			this.user = new Abonne(Integer.parseInt(res.get(0)), res.get(1), res.get(2), Integer.parseInt(res.get(3)),
					res.get(4));
		}
	}

	/**
	 * Film f = le films a louer renvoie int suivant dtatu execution : 1 = solde
	 * insuffisant 2 = autres
	 * 
	 */
	public int louerFilms(Film f) {
		// verification possibilit� d'emprunt
		if (user.autorise_nb_loc()) {
			// TODO: requete insertion emprunt en base de donn�e
			// + set sold user
			// verifier la disponibiliter du film

			//////
			return 0; // ajout temporaire sinon ça ne compile pas
			/////
		} else {
			return 1;
		}
	}

	/**
	 * pr�condition : l'udtilisateur est identifier depuis l'acceuil, le dvd est
	 * ins�r�
	 */
	public void rendreUnFilm() {
		// lecture du dvd :
		if (this.controleur.haveDVD()) {
			String id = this.controleur.lireDVD();
			// verification avec emprunt de l'utilisateur
			// TODO: gestion du dvd ejecter si mauvais , stocker sinon, update
		}
	}
	
	public ArrayList<Film> RechercheParTitre(String titre){
		String sql = "Select * From LesFilms where titre='"+titre+"';";
		return requete.getFilms(sql);
	}
	
	public ArrayList<Film> RechercheParGenre(String genre){
		String sql = "Select * "
				+ "From LesFilms F, LesGenres g "
				+ " where F.titre= g.titre"
				+ " and g.genre = '"+genre+"';";
		return requete.getFilms(sql);
	}
	
	public ArrayList<Film> RechercheParRealisateurs(String rea){
		String sql = "Select *"
				+ " From LesFilms F, LesRealisations R,  "
				+ "where F.titre=R.titre"
				+ "and R.real='"+rea+"';";
		return requete.getFilms(sql);
	}
	
	public ArrayList<Film> RechercheParActeur(String acteur){
		String sql = "Select * "
				+ "From LesFilms F, LesParticipations P "
				+ "where F.titre=P.titre"
				+ "and P.acteur='"+acteur+"';";
		return requete.getFilms(sql);
	}

}
