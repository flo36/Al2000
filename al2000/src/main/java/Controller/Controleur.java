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
	
	public void lireCarteAbo() {
		c_abo.getAllData();
	}
	
	public void lireCarteBleu() {
		c_cb.getAllData();
		
	}
	
	public void UtiliserEcranTactile() {
		c_ec.work();
	}
	
	public void lireDVD() {
		c_dvd.getAllData();
	}
	
	
}
