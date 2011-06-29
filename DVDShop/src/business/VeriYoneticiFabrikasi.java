package business;

import domain.Dvd;
import domain.Musteri;
import domain.PO;
import domain.Personel;

public class VeriYoneticiFabrikasi {

	public VeriYoneticiFabrikasi() {

	}

	public static VeriYoneticisi getInstance(PO po) {

		VeriYoneticisi dataManager = null;

		if (po instanceof Musteri) {
			dataManager = new MusteriVeriYoneticisi();
		} else if (po instanceof Personel) {
			dataManager = new PersonelVeriYoneticisi();
		} else if (po instanceof Dvd) {
			dataManager = new DvdVeriYoneticisi();
		}

		return dataManager;
	}

}
