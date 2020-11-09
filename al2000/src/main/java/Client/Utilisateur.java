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
	
}
