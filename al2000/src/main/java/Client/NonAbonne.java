package Client;

import BDD.Requete;

/**

	getters et setters de la classe Mère :
	- carteBleue
		

	autorise_nb_loc(Utilisateur u)
	-> retourne un boolean qui autorise ou rejette une location sur les critères suivants : 
   		- nombre de location ACTUELLE de l'utilisateur

   
	ajoute_LocBdd(Utilisateur u)
	-> ajoute la location dans la bdd, si c'est autorise 

**/

public class NonAbonne extends Utilisateur {

	static int nb_loc_autorise = 1;
	
	public NonAbonne(int cb)
	{
		super(cb);
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
