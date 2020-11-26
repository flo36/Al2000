package Client;

import cinema.Dvd;

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
	
	public boolean ajouteLocBdd()
	{
		boolean requete_reussie = false;
		//TODO : créer la requête pour ajouter la location à la bdd
		
		return false;	
	}
}
