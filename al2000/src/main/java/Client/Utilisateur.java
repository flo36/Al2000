package Client;

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
	
	abstract Boolean autorise_loc();
	
	public int compte_nb_loc()
	{
		int nb_loc = 0;
		
		//TODO : cr�er une requete qui r�cup�re les locations appartenant � un utilisateur
		
		return nb_loc;
	}
	
}
