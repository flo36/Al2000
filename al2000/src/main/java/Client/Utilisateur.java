package main.java.Client;

import main.java.BDD.Requete;

/**

	getters et setters :
	- carteBleue
	- film_emprunte
	
	Si film_emprunte n'est pas null et l'utilisateur n'est pas abonn�, alors refut de louer

**/

public abstract class Utilisateur {

	private int carteBleue;
	
	public Utilisateur()
	{
		
	}
	
	public Utilisateur(int cb)
	{
		this.carteBleue = cb;
	}
	
	
	public int getCarteBleue() {
		return carteBleue;
	}
	
	public void setCarteBleue(int carteBleue) {
		this.carteBleue = carteBleue;
	}
	
	public int compte_nb_loc()
	{
		int cpt_loc = 0;
		
		Requete r = new Requete();
			
		return cpt_loc;
	}
	
	public abstract boolean autorise_nb_loc();
	public abstract boolean solde_suffisant_loc();
	
	
}
