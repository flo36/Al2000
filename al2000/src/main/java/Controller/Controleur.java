package Controller;

public class Controleur {

	private EcranTactile c_ec;
	private LecteurCarteBleu c_cb;
	private LecteurCarteAbo c_abo;
	private LecteurDVD c_dvd;
	//private MediateurAL media;
	
	public Controleur() {
		this.c_ec 	= new EcranTactile(this);
		this.c_cb 	= new LecteurCarteBleu();
		this.c_abo 	= new LecteurCarteAbo();
		this.c_dvd 	= new LecteurDVD();
	}
	
	//renvoie l'id de l'abonn� present sur la carte
	public String lireCarteAbo() {
		return c_abo.getAllData().get("id");
	}
	
	public String lireCarteBleu() {
		return c_cb.getAllData().get("id");
		
	}
	
	public void UtiliserEcranTactile() {
		c_ec.work();
	}
	
	public String lireDVD() {
		return c_dvd.getAllData().get("id");
	}
	
	public boolean haveCarteAbo() {
		return c_abo.estCharger();
	}
	
	public boolean haveCarteBleu() {
		return c_cb.estCharger();
		
	}
	
	
	public boolean haveDVD() {
		return c_dvd.estCharger();
	}
	
}
