package business;

import java.util.Vector;

import domain.Dvd;
import domain.PO;

/**
 * Bu sýnýf veritabani islemlerini gerceklerstiren siniflara bir yerden
 * erisilmesini saglar. Is katmanýna sadece bu sýnýfý kullanarak erisildiginden
 * bagimliliklar azalir.
 * 
 * @author Tayfun Gokmen Halac, Tugcem Oral
 * 
 */
public class VeriYoneticisiOnCephe {

	VeriYoneticisi yonetici;

	public VeriYoneticisiOnCephe() {

		yonetici = null;
		VeritabaniBaglantisi.baglantiAc();
	}

	/**
	 * Verilen nesneyi veritabanina kaydetme islemini gerceklestiren metod.
	 * 
	 * @param po
	 * @return
	 */
	public boolean kaydet(PO po) {

		yonetici = VeriYoneticiFabrikasi.getInstance(po);
		return yonetici.kaydet(po);
	}

	/**
	 * 
	 * Bu metod gelen iki nesneden ikincisinin farklý olan özelliklerini
	 * birincisine koyarak gunceller.
	 * 
	 * @param po
	 * @param yeniPo
	 * @return
	 */
	public boolean guncelle(PO po, PO yeniPo) {

		yonetici = VeriYoneticiFabrikasi.getInstance(po);
		return yonetici.guncelle(po, yeniPo);
	}

	/**
	 * gelen nesneyi veritabanindan silen metod.
	 * 
	 * @param po
	 * @return
	 */
	public boolean sil(PO po) {

		yonetici = VeriYoneticiFabrikasi.getInstance(po);
		return yonetici.sil(po);
	}

	public Vector<Dvd> fiyatAraligindaAra(Dvd dusukFiyatDvd, Dvd yuksekFiyatDvd) {

		Vector<Dvd> vector = null;
		DvdVeriYoneticisi yonetici = new DvdVeriYoneticisi();

		vector = yonetici.fiyatAraligindaAra(dusukFiyatDvd, yuksekFiyatDvd);
		return vector;
	}

	/**
	 * Bu metod gelen nesnenin null olmayan ozelliklerine gore bir arama yapar.
	 * 
	 * @param po
	 * @return bulunan nesneleri bir vector icerisinde dondurur.
	 */
	public Vector<PO> ara(PO po) {

		yonetici = VeriYoneticiFabrikasi.getInstance(po);
		return yonetici.ara(po);
	}

	/**
	 * Bu metod bir dvd satildiginda kullanilan metoddur.
	 * 
	 * @param satilacak
	 * @return satin alinip alinmadigina dair birkac durumu isaret eden int
	 *         deger dondurur.
	 */
	public int satinAl(PO satilacak) {

		yonetici = VeriYoneticiFabrikasi.getInstance(satilacak);
		if (satilacak instanceof Dvd) {
			return yonetici.satinAl(satilacak);
		} else
			return -1;
	}

}