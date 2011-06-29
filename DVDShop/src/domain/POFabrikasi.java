package domain;

public class POFabrikasi {

	public static PO getInstance(PO po) {

		PO returnPo = null;

		if (po instanceof Musteri) {
			returnPo = new Musteri();
		} else if (po instanceof Personel) {
			returnPo = new Personel();
		} else if (po instanceof Dvd) {
			returnPo = new Dvd();
		}

		return returnPo;

	}
}
