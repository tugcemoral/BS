package business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import domain.Musteri;
import domain.PO;

public class MusteriVeriYoneticisi implements VeriYoneticisi {

	/**
	 * Bu metod musteri nesnesini veritabanina kaydeder
	 * 
	 * @param PO
	 *            po
	 * @return boolean (kaydedildiyse true,diger durumlarda false)
	 */
	public boolean kaydet(PO po) {

		Musteri kaydedilecekMusteri = (Musteri) po;

		String adSoyad = kaydedilecekMusteri.getAdSoyad();
		String kullaniciAdi = kaydedilecekMusteri.getKullaniciAdi();
		String parola = kaydedilecekMusteri.getParola();
		String kartNo = kaydedilecekMusteri.getKrediKartNo();

		try {
			PreparedStatement musteriEklemeDurum = (PreparedStatement) VeritabaniBaglantisi.BAGLANTI
					.prepareStatement("INSERT INTO musteriler (adsoyad, username, password, kartNo) VALUES (?, ?, ?, ?)");

			musteriEklemeDurum.setString(1, adSoyad);
			musteriEklemeDurum.setString(2, kullaniciAdi);
			musteriEklemeDurum.setString(3, parola);
			musteriEklemeDurum.setString(4, kartNo);

			int i = musteriEklemeDurum.executeUpdate();

			musteriEklemeDurum.close();
			if (i == 1)
				return true;
			else
				return false;
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
			return false;
		}
	}

	public Vector<PO> ara(PO po) {

		Vector<PO> bulunanMusteriler = new Vector<PO>();
		String sorgu = sorguHazirla(po);

		try {
			Statement aramaSorgusu = VeritabaniBaglantisi.BAGLANTI
					.createStatement();
			ResultSet resultSet = aramaSorgusu.executeQuery(sorgu);

			while (resultSet.next()) {
				PO musteri = new Musteri(resultSet.getString("adsoyad"),
						resultSet.getString("username"), resultSet
								.getString("password"), resultSet
								.getString("kartNo"));
				bulunanMusteriler.addElement(musteri);
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		}

		return bulunanMusteriler;
	}

	public boolean guncelle(PO po, PO yeniPo) {

		boolean sonuc;
		Musteri guncellenenMusteri = (Musteri) po;
		Musteri yeniMusteri = (Musteri) yeniPo;

		sonuc = sil(po);

		if (sonuc == false)
			return sonuc;
		if (!(yeniMusteri.getAdSoyad().equals("") || yeniMusteri.getAdSoyad()
				.equals(null)))
			guncellenenMusteri.setAdSoyad(yeniMusteri.getAdSoyad());
		if (!(yeniMusteri.getID().equals("") || yeniMusteri.getID()
				.equals(null)))
			guncellenenMusteri.setKullaniciAdi(yeniMusteri.getID());
		if (!(yeniMusteri.getParola().equals("") || yeniMusteri.getParola()
				.equals(null)))
			guncellenenMusteri.setParola(yeniMusteri.getParola());
		if (!(yeniMusteri.getKrediKartNo().equals("") || yeniMusteri
				.getKrediKartNo().equals(null)))
			guncellenenMusteri.setKrediKartNo(yeniMusteri.getParola());

		kaydet(guncellenenMusteri);

		return sonuc;
	}

	public boolean sil(PO po) {

		Musteri silinenMusteri = (Musteri) po;
		String silinecekID = silinenMusteri.getID();

		try {
			PreparedStatement silmeDurumu = (PreparedStatement) VeritabaniBaglantisi.BAGLANTI
					.prepareStatement("DELETE FROM musteriler WHERE userName=?");
			silmeDurumu.setString(1, silinecekID);
			int i = silmeDurumu.executeUpdate();
			silmeDurumu.close();

			if (i == 1)
				return true;
			else
				return false;
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
			return false;
		}

	}

	/**
	 * @deprecated bu sýnýfta bu metota gereksinim duyulmamaktadir.
	 */
	public int satinAl(PO satilacak) {
		return 0;
	}

	public String sorguHazirla(PO po) {

		Musteri arananMusteri = (Musteri) po;

		// aranan musterilerin aranmasý gereken özelligini aldýk.
		String kullaniciAdi = arananMusteri.getID();
		String sorgu = "SELECT * FROM musteriler ";

		if ((kullaniciAdi != null) && !(kullaniciAdi.trim().equals(""))) {
			sorgu += "WHERE username = '" + kullaniciAdi + "'";
		}

		return sorgu;
	}

}
