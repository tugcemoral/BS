package tr.ege;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;
import business.DvdVeriYoneticisi;
import business.VeriYoneticisiOnCephe;
import business.VeritabaniBaglantisi;
import business.VeritabaniniIlklendir;
import domain.Dvd;
import domain.PO;
import domain.Personel;

public class DVDTest extends TestCase {

	@Override
	protected void setUp() throws Exception {

		VeritabaniBaglantisi.baglantiAc();

		// Form icindeki baglanti noktalari tek oldugundan ve giris
		// formundan alindigindan burada ilklendirilmesi gerekiyor.

		// Veritabani tablolari duzenlenip tekrar aciliyor.
		VeritabaniniIlklendir.tablolariHazirla();
	}

	@Override
	protected void tearDown() throws Exception {

		VeritabaniBaglantisi.BAGLANTI.close();

	}

	public void testYeniDVDKaydet() throws SQLException {

		// facade olusturularak PO nesnesi ile gui nin veriYonetici sinifiyla
		// baglantisi kopariliyor.
		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();

		// yeni bir urun kaydediliyor.
		PO kaydedilecekDvd = new Dvd("12345", "filmAdi", 74, 6, "yonetmen",
				"sirket");
		assertTrue(facade.kaydet(kaydedilecekDvd));

		// ekleme sonucu kontrol ediliyor...
		Statement st = VeritabaniBaglantisi.BAGLANTI.createStatement();
		ResultSet resultSet = st.executeQuery("SELECT * FROM dvdler");

		// dvdnin tum ozellikleri kontrol ediliyor.
		assertTrue(resultSet.next());

		assertEquals("filmAdi", resultSet.getString("filmAdi"));
		assertEquals("yonetmen", resultSet.getString("yonetmen"));
		assertEquals(74, resultSet.getInt("fiyat"));
		assertEquals(12345, resultSet.getInt("barkod"));
		assertEquals("sirket", resultSet.getString("sirket"));
		assertEquals(6, resultSet.getInt("adet"));

	}

	public void testAra() throws SQLException {

		// yeni bir dvd kaydediliyor
		testYeniDVDKaydet();

		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();

		// filmAdina gore urun araniyor
		PO aranacakDvd = new Dvd(null, "filmAdi", 1, 1, null, "");
		assertFalse(facade.ara(aranacakDvd).isEmpty());

		// olmayan isimde bir film araniyor.
		((Dvd) aranacakDvd).setUrunAdi("xxx");
		assertTrue(facade.ara(aranacakDvd).isEmpty());

		// Ara metoduna ozelliklerinin tumu null olan bir nesne geldiginde
		//onun da dondugunun kontrol edilmesi.
		PO personel = new Personel();
		assertFalse((facade.ara(personel)).isEmpty());

		// yonetmene gore arama yapiliyor.
		((Dvd) aranacakDvd).setYonetmen("yonetmen");
		((Dvd) aranacakDvd).setUrunAdi(null);
		assertFalse(facade.ara(aranacakDvd).isEmpty());
		
		// olmayan isimde bir film araniyor.
		((Dvd) aranacakDvd).setYonetmen("xxx");
		assertTrue(facade.ara(aranacakDvd).isEmpty());

		// barkoda gore urun araniyor...
		((Dvd) aranacakDvd).setYonetmen(null);
		((Dvd)aranacakDvd).setID("12345");
		assertFalse(facade.ara(aranacakDvd).isEmpty());
		
		//olmayan barkodda urun araniyor..
		((Dvd)aranacakDvd).setID("2135");
		assertTrue(facade.ara(aranacakDvd).isEmpty());
	}

	public void testUrunSilme() throws SQLException {

		// yeni bir dvd kaydediliyor
		testYeniDVDKaydet();

		// facade olusturuldu. PO nesnesiyle guinin veriYoneticisi siniflariyla
		// baglantisi kesildi.
		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();

		// eklenen tek urun siliniyor
		PO silinecekDvd = new Dvd("12345", "", 0, 0, "", "");
		assertTrue(facade.sil(silinecekDvd));

	}

	public void testDvdGuncelle() throws SQLException {

		// yeni bir dvd kaydediliyor.
		testYeniDVDKaydet();

		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();

		// olmayan dvd araniyor.
		PO guncellenecekDvd = new Dvd("56784", "aa", 56, 5, "bb", "tt");
		PO yeniDvd = new Dvd("", "", 0, 0, "", "");
		assertFalse(facade.guncelle(guncellenecekDvd, yeniDvd));

		// olan dvd guncelleniyor.
		((Dvd) guncellenecekDvd).setUrunAdi("filmAdi");
		((Dvd) guncellenecekDvd).setYonetmen("yonetmen");
		((Dvd) guncellenecekDvd).setFiyat(74);
		((Dvd) guncellenecekDvd).setID("12345");
		((Dvd) guncellenecekDvd).setSirket("sirket");
		((Dvd) guncellenecekDvd).setAdet(6);

		((Dvd) yeniDvd).setUrunAdi("aa");
		((Dvd) yeniDvd).setYonetmen("bb");
		((Dvd) yeniDvd).setFiyat(56);
		((Dvd) yeniDvd).setID("56784");
		((Dvd) yeniDvd).setSirket("");
		((Dvd) yeniDvd).setAdet(5);

		assertTrue(facade.guncelle(guncellenecekDvd, yeniDvd));

		Statement st = VeritabaniBaglantisi.BAGLANTI.createStatement();
		ResultSet resultSet = st.executeQuery("SELECT * FROM dvdler");

		// dvdnin tum ozellikleri kontrol ediliyor.
		assertTrue(resultSet.next());

		assertEquals("aa", resultSet.getString("filmAdi"));
		assertEquals("bb", resultSet.getString("yonetmen"));
		assertEquals(56, resultSet.getInt("fiyat"));
		assertEquals(56784, resultSet.getInt("barkod"));
		assertEquals("sirket", resultSet.getString("sirket"));
		assertEquals(5, resultSet.getInt("adet"));

	}

	public void testFiyatAraligindaAra() throws SQLException {

		// yeni bir dvd kaydediliyor
		testYeniDVDKaydet();

		Dvd dusukFiyatDvd = new Dvd("", "", 12, 0, "", "");
		Dvd yuksekFiyatDvd = new Dvd("", "", 100, 0, "", "");

		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();
		// bir tane DVD bulmasý bekleniyor..
		assertFalse(facade.fiyatAraligindaAra(dusukFiyatDvd, yuksekFiyatDvd)
				.isEmpty());

		yuksekFiyatDvd.setFiyat(30);
		// olmayan aranýyor..
		assertTrue((new DvdVeriYoneticisi()).fiyatAraligindaAra(dusukFiyatDvd,
				yuksekFiyatDvd).isEmpty());

	}

	public void testSatinAl() {

		// satilacak DVD yaratiliyor..
		Dvd satilacakDvd = new Dvd("12345", "", 0, 6, "", "");

		// stoktaki urunun satilmasinin kontrolu..
		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();
		assertEquals(1, facade.satinAl(satilacakDvd));

		// Dvd dýþýnda bir tip verilerek kontrol ediliyor.
		PO satilacak = new Personel();
		assertEquals(-1, facade.satinAl(satilacak));

		// stokta olmayan urunun satilmasi..
		satilacakDvd.setAdet(0);
		assertEquals(0, facade.satinAl(satilacakDvd));
	}

}
