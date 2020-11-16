package Client;

import java.util.ArrayList;

import cinema.Genre;

public class Abonne extends Utilisateur {

	String nom;
	String prenom;
	int age;
	String email;
	int solde;
	ArrayList<Genre> restrictions = new ArrayList<Genre>();
	ArrayList<Location> historique = new ArrayList<Location>();
	static int nb_loc_autorise = 3;
	
	//le solde de la carte doit être supérieur à 15 euros mais c'est l'utilisateur qui doit initialiser la valeur de son solde
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
	
	public Boolean autorise_loc()
	{
		//on vérifie le nombre de location en cours pour l'utilisateur
		int nb_loc_en_cours = this.compte_nb_loc();
		
		if(nb_loc_en_cours >= nb_loc_autorise)
		{
			return false;
		}	
		return true;
	}
	
}
