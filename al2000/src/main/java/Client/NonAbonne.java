package src.main.java.Client;

import src.main.java.BDD.Requete;
import src.main.java.Cinema.Film;

/**

	getters et setters de la classe :
	- carteBleue
	- loc
		

	autorise_nb_loc(Utilisateur u)
	-> retourne un boolean qui autorise ou rejette une location sur les critères suivants : 
   		- nombre de location ACTUELLE de l'utilisateur

   
	ajoute_LocBdd(Utilisateur u)
	-> ajoute la location dans la bdd, si c'est autorise 

**/

public class NonAbonne extends Utilisateur {

	static int nb_loc_autorise = 1;
	private Film loc = null;
	
	public NonAbonne(int cb)
	{
		super(cb);
	}
	
	public Film getLoc() {
		return loc;
	}
	
	public void setLoc(Film film_emprunte) {
		this.loc = film_emprunte;
	}
	
	public boolean autorise_nb_loc()
	{
		//on vérifie le nombre de location en cours pour l'utilisateur
		int nb_loc_en_cours = this.compte_nb_loc();
		
		if(nb_loc_en_cours >= nb_loc_autorise)
		{
			return false;
		}	
		return true;
	}
	
	public boolean solde_suffisant_loc()
	{
		//le non abonne paye avec sa carte, on ne peut donc pas regarder son solde
		return true;
	}
	
	public int compte_nb_loc()
	{
		return this.getLoc() != null ? 1 : 0;
	}
	
/////////////////////////////////////////////////requete BDD///////////////////////////////////////////////
	
	public void enregistre_Non_Abonne()
	{
		
		Requete r = new Requete();
		String valeurs = "";
		valeurs+= Integer.toString(this.getCarteBleue());
		
		//ajout dans la bdd, voir class Requete
		r.setUser(valeurs);
	}
	
}
