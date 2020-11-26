package src.main.java.Client;

import src.main.java.Cinema.Dvd;

/**
	
	getters et setters pour les attributs suivants :
	- id_loc
	- utilisateur
	- dvd
	
	autorise_loc(Utilisateur u)
	-> retourne un boolean qui autorise ou rejette une location sur les crit�res suivants : 
	   - du nombre de location ACTUELLE de l'utilisateur
	   - le solde de la carte d'abonne si c'est un abonne sinon toujours bon
	   
	ajoute_LocBdd(Utilisateur u)
	-> ajoute la location dans la bdd, si c'est autorise 

**/

public class Location {
	
	private int id_loc = 0;
	private Utilisateur user;
	private Dvd dvd;
	
	public Location()
	{
		
	}
	
	public Location(Utilisateur _user, Dvd _dvd)
	{
		this.user = _user;
		this.dvd = _dvd;
	}
	
/////////////////////////////////////debut getters && setters///////////////////////////////////////

	
	public Dvd getDvd() {
		return dvd;
	}
	
	public int getId_loc() {
		return id_loc;
	}
	
	public Utilisateur getUser() {
		return user;
	}
	
	public void setDvd(Dvd dvd) {
		this.dvd = dvd;
	}
	
	public void setId_loc(int id_loc) {
		this.id_loc = id_loc;
	}
	
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
/////////////////////////////////////fin getters && setters///////////////////////////////////////
	
	public boolean autorise_loc()
	{
		
		//on regarde si l'utilisateur n'a pas d�j� louer le nb max de dvd a la fois
		if(!user.autorise_nb_loc())
		{
			//la location n'est pas autoris�e, l'utilisateur a d�j� atteint son nb de location en m�me temps
			return false;
		}
		
		//on regarde si le solde de l'utilisateur est suffisant pour louer un dvd
		//pour le non abonne, il pourra toujours : on ne regarde pas son solde de cb
		//pour l'abonne on regarde le solde de sa carte d'abonne
		if(!user.solde_suffisant_loc())
		{
			return false;
		}
		
		return true;
	}
	

	
	public boolean ajoute_LocBdd()
	{
		//TODO : cr�er la requ�te pour  la location � la bdd
		
		boolean location_autorise = this.autorise_loc();
		
		if(location_autorise)
		{
			String valeurs = "";
			
			
		}
		
		return location_autorise;	
	}
}
