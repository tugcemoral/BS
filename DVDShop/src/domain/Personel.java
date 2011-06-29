package domain;
/**
 * 
 * @author Tugcem Oral, Tayfun Gokmen Halac
 * Personel nesnesinin sýnýfýdýr ve PO interface inden türer.
 *
 */

public class Personel extends Uye {

	public Personel() {

	}

	public Personel(String adSoyad, String kullaniciAdi, String parola) {

		super(adSoyad, kullaniciAdi, parola);

	}

	public String getID() {

		return super.kullaniciAdi;

	}

	public void setID(String id) {

		this.kullaniciAdi = id;

	}

}
