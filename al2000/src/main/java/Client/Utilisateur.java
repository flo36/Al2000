package src.main.java.Client;

import src.main.java.Cinema.Film;

/**

	getters et setters :
	- carteBleue
	- film_emprunte
	
	Si film_emprunte n'est pas null et l'utilisateur n'est pas abonné, alors refut de louer

**/

public abstract class Utilisateur {

	private int carteBleue;
	
	
	
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
	
	abstract boolean autorise_nb_loc();
	abstract boolean solde_suffisant_loc();
	
	
}
