package tr.ege;

import junit.framework.TestCase;
import business.VeriYoneticiFabrikasi;
import business.VeriYoneticisi;
import domain.Dvd;
import domain.Musteri;
import domain.PO;
import domain.Personel;

public class ManagerFactoryTest extends TestCase {

	public void testDvd() {

		PO dvd = new Dvd();		
		VeriYoneticisi trueOrNot = VeriYoneticiFabrikasi.getInstance(dvd);
		assertTrue(trueOrNot instanceof VeriYoneticisi);

	}

	public void testPersonel() {

		PO personel = new Personel();		
		VeriYoneticisi trueOrNot = VeriYoneticiFabrikasi.getInstance(personel);
		assertTrue(trueOrNot instanceof VeriYoneticisi);

	}

	public void testMusteri() {

		PO musteri = new Musteri();
		VeriYoneticisi trueOrNot = VeriYoneticiFabrikasi.getInstance(musteri);
		assertTrue(trueOrNot instanceof VeriYoneticisi);

	}
	
	public void testDiger() {
		
		PO po = new PO(){

			public String getID() {				
				return null;
			}

			public void setID(String id) {
				
			}
						
		};		
		VeriYoneticisi trueOrNot = VeriYoneticiFabrikasi.getInstance(po);
		assertFalse(trueOrNot instanceof VeriYoneticisi);
	}

}
