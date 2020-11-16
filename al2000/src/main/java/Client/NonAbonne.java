package Client;

public class NonAbonne extends Utilisateur {

	static int nb_loc_autorise = 1;
	
	public NonAbonne(int cb)
	{
		super(cb);
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
