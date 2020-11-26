package src.main.java.Controller;

import java.util.HashMap;

public abstract class Lecteur {
	private HashMap<String, String> data;
	private boolean detecteur;
	
	/** renvoie un lecteur de carte quelqu'onque initialisé avec une carte a l'interieur*/
	public Lecteur() {
		this.detecteur = true;
		this.data = new HashMap<String, String>();
	}
	
	/** renvoie true si le support a lire est inseré*/
	protected boolean estCharge() {
		return this.detecteur;
	}
	
	/** retourne la valeur de la donnée de titre data sous forme de String */
	protected String getData(String data) {
		return this.getAllData().get(data);
	}

	/** retourne toutes les données stocker sur la carte sous forme de tableau <String key -> String value> */
	public HashMap<String, String> getAllData() {
		return data;
	}

	private void setData(HashMap<String, String> data) {
		this.data = data;
	}
	
	
}
