package Client;


/**

	getters et setters :
	- carteBleue

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
	
	public int compte_nb_loc()
	{
		int nb_loc = 0;
		
		//TODO : cr�er une requete qui r�cup�re les locations appartenant � un utilisateur
		
		return nb_loc;
	}
	
}
