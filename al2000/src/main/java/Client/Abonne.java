package src.main.java.Client;

import java.util.ArrayList;

import src.main.java.BDD.Requete;
import src.main.java.Cinema.Film;
import src.main.java.Cinema.Genre;




/**

	getters et setters de la classe M�re :
	- carteBleue
	
	getters pour les attributs suivants :
	- nom
	- prenom
	- age
	- email
	- solde
	- restrictions
	- historique

	setters pour les attributs suivants :
	- email
	- age
	- solde
	- restrictions
	
	
	autorise_nb_loc(Utilisateur u) et solde_suffisant_loc
	-> retourne un boolean qui autorise ou rejette une location sur les crit�res suivants : 
		- nombre de location ACTUELLE de l'utilisateur
		- le solde de la carte d'abonne
		
	enregistre_Abonne
	->enregistre la location dans la bdd si cela est autorise
	

ajoute_LocBdd(Utilisateur u)
-> ajoute la location dans la bdd, si c'est autorise 

**/

public class Abonne extends Utilisateur {

	String nom;
	String prenom;
	int age;
	String email;
	int solde;
	ArrayList<Genre> restrictions = new ArrayList<Genre>();
	ArrayList<Location> historique = new ArrayList<Location>();
	Film loc1;
	Film loc2;
	Film loc3;
	static int nb_loc_autorise = 3;
	
	//le solde de la carte doit �tre sup�rieur � 15 euros mais c'est l'utilisateur qui doit initialiser la valeur de son solde
	public Abonne(int cb,String _nom, String _prenom, int _age, String _email)
	{
		super(cb);
		this.nom = _nom;
		this.prenom = _prenom;
		this.nom = _nom;
		this.age = _age;
		this.email = _email;
		this.solde = 0;
	}
	
	///////////////////////////////////////debut getters && setters///////////////////////////////////////
	public int getAge() {
		return age;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public int getSolde() {
		return solde;
	}
	
	public ArrayList<Genre> getRestrictions() {
		return restrictions;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setSolde(int solde) {
		this.solde = solde;
	}
	
	public void setRestrictions(ArrayList<Genre> restrictions) {
		this.restrictions = restrictions;
	}
	
	public ArrayList<Location> getHistorique() {
		return historique;
	}
	
	public Film getLoc1() {
		return loc1;
	}
	
	public Film getLoc2() {
		return loc2;
	}
	
	public Film getLoc3() {
		return loc3;
	}
	
	public void setLoc1(Film loc1) {
		this.loc1 = loc1;
	}
	
	public void setLoc2(Film loc2) {
		this.loc2 = loc2;
	}
	
	public void setLoc3(Film loc3) {
		this.loc3 = loc3;
	}
	
///////////////////////////////////////fin getters && setters///////////////////////////////////////
	
	
	//l'utilisateur doit saisir le montant
	public void initialierSolde(int solde_init)
	{
		this.solde = solde_init;
	}
	
	public void ajoutRestriction(Genre genre_restreint)
	{
		this.restrictions.add(genre_restreint);
	}
	
	public void enleveRestriction(Genre genre_restreint)
	{
		this.restrictions.remove(genre_restreint);
	}
	
	public int compte_nb_loc()
	{
		int cpt_loc =0;
		cpt_loc = this.getLoc1() != null ? cpt_loc+1 : cpt_loc;
		cpt_loc = this.getLoc2() != null ? cpt_loc+1 : cpt_loc;
		cpt_loc = this.getLoc3() != null ? cpt_loc+1 : cpt_loc;
			
		return cpt_loc;
	}
	
	public boolean autorise_nb_loc()
	{
		//on v�rifie le nombre de location en cours pour l'utilisateur
		int nb_loc_en_cours = this.compte_nb_loc();
		
		if(nb_loc_en_cours >= nb_loc_autorise)
		{
			return false;
		}	
		
		return true;
	}
	
	public boolean solde_suffisant_loc()
	{
		//on regarde si le solde de la carte est suffisant
		if(this.getSolde() >= 3)
		{
			//le solde est suffisant
			return true;
		}
		else
		{
			//le solde n'est pas suffisant, il faut qu'il recharge sa carte
			return false;
		}	
		
	}
	
/////////////////////////////////////////////////requete BDD///////////////////////////////////////////////
	
	public void enregistre_Abonne()
	{
		Requete r = new Requete();
		
		String valeurs = "";
		valeurs+= this.getNom()+", ";
		valeurs+= this.getPrenom()+", ";
		valeurs+= this.getEmail()+", ";
		valeurs+= Integer.toString(this.getAge())+", ";
		valeurs+= Integer.toString(this.getCarteBleue())+", ";
		
		//ajouter les restrictions si elles existent
		if(this.getRestrictions()!= null)
		{
			//TODO : d�pend de la conception de la bdd
		}
		
		//ajout dans la bdd, voir class Requete
		r.setUser(valeurs);
	}
	
}
