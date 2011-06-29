package business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import domain.PO;
import domain.Personel;

public class PersonelVeriYoneticisi implements VeriYoneticisi {

	public boolean kaydet(PO po) {

		Personel kaydedilenPersonel = (Personel) po;
		String kullaniciAdi = kaydedilenPersonel.getKullaniciAdi();
		String parola = kaydedilenPersonel.getParola();
		String adSoyad = kaydedilenPersonel.getAdSoyad();

		try {
			PreparedStatement kaydetmeSorgusu = (PreparedStatement) VeritabaniBaglantisi.BAGLANTI
					.prepareStatement("INSERT INTO personel (adsoyad, username, password) VALUES (?, ?, ?)");
			kaydetmeSorgusu.setString(1, adSoyad);
			kaydetmeSorgusu.setString(2, kullaniciAdi);
			kaydetmeSorgusu.setString(3, parola);
			int i = kaydetmeSorgusu.executeUpdate();
			kaydetmeSorgusu.close();
			if (i == 1)
				return true;
			else
				return false;
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
			return false;
		}
	}

	public Vector<PO> ara(PO po) {

		Vector<PO> bulunanPersoneller = new Vector<PO>();
		String sorgu = sorguHazirla(po);

		try {
			Statement aramaSorgusu = VeritabaniBaglantisi.BAGLANTI
					.createStatement();
			ResultSet resultSet = aramaSorgusu.executeQuery(sorgu);

			while (resultSet.next()) {
				PO personel = new Personel(resultSet.getString("adsoyad"),
						resultSet.getString("username"), resultSet
								.getString("password"));
				bulunanPersoneller.addElement(personel);
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		}

		return bulunanPersoneller;
	}

	public boolean guncelle(PO eskiPo, PO yeniPo) {

		boolean sonuc;
		Personel guncellenenPersonel = (Personel) eskiPo;
		Personel yeniPersonel = (Personel) yeniPo;

		sonuc = sil(eskiPo);
		if (sonuc == false)
			return sonuc;

		if (!(yeniPersonel.getAdSoyad().equals("")))
			guncellenenPersonel.setAdSoyad(yeniPersonel.getAdSoyad());
		if (!(yeniPersonel.getID().equals("")))
			guncellenenPersonel.setKullaniciAdi(yeniPersonel.getID());
		if (!(yeniPersonel.getParola().equals("")))
			guncellenenPersonel.setParola(yeniPersonel.getParola());

		sonuc &= kaydet(guncellenenPersonel);

		return sonuc;
	}

	public boolean sil(PO po) {

		String silinecekID = po.getID();

		try {
			PreparedStatement silmeSorgusu = (PreparedStatement) VeritabaniBaglantisi.BAGLANTI
					.prepareStatement("DELETE FROM personel WHERE userName=?");
			silmeSorgusu.setString(1, silinecekID);
			int i = silmeSorgusu.executeUpdate();
			silmeSorgusu.close();
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
	 * @deprecated bu s�n�fta bu metota gereksinim duyulmamaktadir.
	 */

	public int satinAl(PO satilacak) {
		return 0;
	}

	public String sorguHazirla(PO po) {

		Personel arananPersonel = (Personel) po;

		// aranan personelin aranmasi gereken �zelligini ald�k.
		String kullaniciAdi = arananPersonel.getID();
		String sorgu = "SELECT * FROM personel ";

		if ((kullaniciAdi != null) && !(kullaniciAdi.trim().equals(""))) {
			sorgu += "WHERE username = '" + kullaniciAdi + "'";
		}

		return sorgu;
	}

}
