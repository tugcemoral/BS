package business;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import domain.Dvd;
import domain.PO;

public class DvdVeriYoneticisi implements VeriYoneticisi {

	/**
	 * Bu metod dvd nesnesini veritabanina kaydeder
	 * 
	 * @param PO
	 *            po
	 * @return boolean (kaydedildiyse true,diger durumlarda false)
	 */
	public boolean kaydet(PO po) {
		// PO tipindeki nesne dvdye çevriliyor.
		Dvd dvd = (Dvd) po;

		try {
			PreparedStatement eklemeSorgusu = (PreparedStatement) VeritabaniBaglantisi.BAGLANTI
					.prepareStatement("INSERT INTO dvdler (filmAdi, yonetmen, fiyat, barkod, sirket, adet) VALUES (?, ?, ?, ?, ?, ?)");
			eklemeSorgusu.setString(1, dvd.getUrunAdi());
			eklemeSorgusu.setString(2, dvd.getYonetmen());
			eklemeSorgusu.setInt(3, Integer.valueOf(dvd.getFiyat()).intValue());
			eklemeSorgusu.setString(4, dvd.getID());
			eklemeSorgusu.setString(5, dvd.getSirket());
			eklemeSorgusu.setInt(6, Integer.valueOf(dvd.getAdet()).intValue());
			int i = eklemeSorgusu.executeUpdate();
			eklemeSorgusu.close();

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

	/**
	 * This method deletes dvd object.
	 * 
	 * @return boolean (silindiyse true diger durumlarda false)
	 * @param PO
	 *            po
	 */
	public boolean sil(PO po) {
		// silinecek nesnenin ID degeri aliniyor.
		String silinecekBarkod = ((Dvd) po).getID();

		try {
			PreparedStatement silmeSorgusu = (PreparedStatement) VeritabaniBaglantisi.BAGLANTI
					.prepareStatement("DELETE FROM dvdler WHERE barkod=?");
			silmeSorgusu.setString(1, silinecekBarkod);
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
	 * This method updates oldDvd object thought newDvd object.
	 * 
	 * @param po,
	 *            yeniPO
	 * @return boolean
	 */
	public boolean guncelle(PO po, PO yeniPo) {
		// eski ve yeni nesneler dvdye cevriliyor.
		Dvd guncellenenDvd = (Dvd) po;
		Dvd yeniDvd = (Dvd) yeniPo;

		try {

			PreparedStatement silinenDurum = (PreparedStatement) VeritabaniBaglantisi.BAGLANTI
					.prepareStatement("DELETE FROM dvdler WHERE barkod=?");
			silinenDurum.setString(1, guncellenenDvd.getID());

			int i = silinenDurum.executeUpdate();

			silinenDurum.close();

			if (i == 0)
				return false;

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}

		if (!(yeniDvd.getUrunAdi().equals("")))
			guncellenenDvd.setUrunAdi(yeniDvd.getUrunAdi());

		if (!(yeniDvd.getYonetmen().equals("")))
			guncellenenDvd.setYonetmen(yeniDvd.getYonetmen());

		if (!(yeniDvd.getFiyat() == -1))
			guncellenenDvd.setFiyat(yeniDvd.getFiyat());

		if (!(yeniDvd.getID().equals("")))
			guncellenenDvd.setID(yeniDvd.getID());

		if (!(yeniDvd.getSirket().equals("")))
			guncellenenDvd.setSirket(yeniDvd.getSirket());

		if (!(yeniDvd.getAdet() == -1))
			guncellenenDvd.setAdet(yeniDvd.getAdet());

		try {

			PreparedStatement kaydedilenDurum = (PreparedStatement) VeritabaniBaglantisi.BAGLANTI
					.prepareStatement("INSERT INTO dvdler (filmAdi, yonetmen, fiyat, barkod, sirket, adet) VALUES (?, ?, ?, ?, ?, ?)");

			kaydedilenDurum.setString(1, guncellenenDvd.getUrunAdi());
			kaydedilenDurum.setString(2, guncellenenDvd.getYonetmen());
			kaydedilenDurum.setInt(3, guncellenenDvd.getFiyat());
			kaydedilenDurum.setString(4, guncellenenDvd.getID());
			kaydedilenDurum.setString(5, guncellenenDvd.getSirket());
			kaydedilenDurum.setInt(6, guncellenenDvd.getAdet());

			int i = kaydedilenDurum.executeUpdate();

			kaydedilenDurum.close();

			if (i == 0)
				return false;
			else
				return true;

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
			return false;
		}

	}

	/**
	 * This method searchs Dvd object between its dusukFiyat and yuksekFiyat..
	 * 
	 * @param dusukFiyatDvd
	 * @param yuksekFiyatDvd
	 * @return
	 */
	public Vector<Dvd> fiyatAraligindaAra(Dvd dusukFiyatDvd, Dvd yuksekFiyatDvd) {

		Vector<Dvd> bulunanDvdler = new Vector<Dvd>();

		try {

			PreparedStatement fiyatAraligindaSorgu = (PreparedStatement) VeritabaniBaglantisi.BAGLANTI
					.prepareStatement("SELECT * FROM dvdler WHERE fiyat > ? AND fiyat < ?;");
			fiyatAraligindaSorgu.setInt(1, dusukFiyatDvd.getFiyat());
			fiyatAraligindaSorgu.setInt(2, yuksekFiyatDvd.getFiyat());

			ResultSet fiyatAraligindaAramaSonuc = fiyatAraligindaSorgu
					.executeQuery();

			while (fiyatAraligindaAramaSonuc.next()) {

				Dvd bulunanDvd = new Dvd(fiyatAraligindaAramaSonuc
						.getString("barkod"), fiyatAraligindaAramaSonuc
						.getString("filmAdi"), fiyatAraligindaAramaSonuc
						.getInt("fiyat"), fiyatAraligindaAramaSonuc
						.getInt("adet"), fiyatAraligindaAramaSonuc
						.getString("yonetmen"), fiyatAraligindaAramaSonuc
						.getString("sirket"));

				bulunanDvdler.addElement(bulunanDvd);

			}
			fiyatAraligindaSorgu.close();

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}

		return bulunanDvdler;
	}

	/**
	 * This method sells Dvd
	 * 
	 * @param po
	 * @return
	 */
	public int satinAl(PO po) {

		Dvd satilacakDvd = (Dvd) po;
		if (Integer.valueOf(satilacakDvd.getAdet()).intValue() > 0) {
			try {

				PreparedStatement pstmt1 = (PreparedStatement) VeritabaniBaglantisi.BAGLANTI
						.prepareStatement("UPDATE dvdler SET adet = adet-1 WHERE barkod=?");
				satilacakDvd.setAdet(satilacakDvd.getAdet() - 1);
				pstmt1.setString(1, satilacakDvd.getID());
				pstmt1.executeUpdate();
				pstmt1.close();

				return 1;// satin alindi.

			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				e1.printStackTrace();
				return 2;// hata olustu.
			}

		} else
			return 0;// stokta yok.
	}

	/**
	 * This method lists films
	 * 
	 * @return
	 */
	public Vector<Dvd> filmleriListele() {

		Dvd geciciDvd = null;
		Vector<Dvd> geciciDvdListesi = new Vector<Dvd>();

		try {
			Statement yeniStmt = VeritabaniBaglantisi.BAGLANTI
					.createStatement();
			ResultSet resultSet = yeniStmt.executeQuery("SELECT * FROM dvdler");

			while (resultSet.next()) {
				geciciDvd = new Dvd(resultSet.getString("barkod"), resultSet
						.getString("filmAdi"), resultSet.getInt("fiyat"),
						resultSet.getInt("adet"), resultSet
								.getString("yonetmen"), resultSet
								.getString("sirket"));

				geciciDvdListesi.addElement(geciciDvd);

			}

			yeniStmt.close();

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}

		return geciciDvdListesi;
	}

	/**
	 * This method searchs for persistant object and returns founded value.
	 * 
	 * @param po
	 * @return
	 */
	public Vector<PO> ara(PO po) {

		Vector<PO> bulunanDvdler = new Vector<PO>();

		String sorgu = sorguHazirla(po);

		try {
			Statement aramaSorgusu = VeritabaniBaglantisi.BAGLANTI.createStatement();
			ResultSet resultSet = aramaSorgusu.executeQuery(sorgu);
			
			while (resultSet.next()) {
			Dvd bulunanDvd = new Dvd(resultSet.getString("barkod"), resultSet
					.getString("filmAdi"), resultSet.getInt("fiyat"), resultSet
					.getInt("adet"), resultSet.getString("yonetmen"), resultSet
					.getString("sirket"));
			bulunanDvdler.addElement(bulunanDvd);
			}
			return bulunanDvdler;

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
			return null;
		}
	}

	public String sorguHazirla(PO po) {

		Dvd arananDvd = (Dvd) po;

		// aranan dvd nin aranmasý gereken özelliklerini aldýk.
		String filmAdi = arananDvd.getUrunAdi();
		String yonetmenAdi = arananDvd.getYonetmen();
		String barkod = arananDvd.getID();
		boolean sorguBirlesti = false;

		String sorgu = "SELECT * FROM dvdler ";
		if ((barkod != null) && !(barkod.trim().equals(""))) {
			sorgu += "WHERE barkod = '" + barkod + "'";
			return sorgu;
		}
		if ((yonetmenAdi != null) && !(yonetmenAdi.trim().equals(""))) {
			sorgu += "WHERE yonetmen = '" + yonetmenAdi + "'";
			sorguBirlesti = true;
		}
		if ((filmAdi != null) && !(filmAdi.trim().equals(""))) {
			if (sorguBirlesti == false)
				sorgu += "WHERE filmAdi = '" + filmAdi + "'";
			else
				sorgu += " AND filmAdi = '" + filmAdi + "'";
		}
		return sorgu;
	}
}