package tr.ege;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import junit.framework.TestCase;
import business.VeriYoneticisiOnCephe;
import business.VeritabaniBaglantisi;
import business.VeritabaniniIlklendir;
import domain.Musteri;
import domain.PO;

public class MusteriTest extends TestCase {

	public void testMusteriEkleme() throws SQLException {
		// veritabani tablolari ilklendirilmesi ve baglanti olusturulmasi
		VeritabaniniIlklendir.tablolariHazirla();
		VeritabaniBaglantisi.baglantiAc();

		// facade olusturuldu ve gui nin veri yoneticisi ile olan baglantisi
		// koparildi.
		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();
		// aranacak kalici nesne PO tipinde tanimlandi.
		PO musteri = new Musteri("ad soyad", "kullanici adi", "sifre", "5555");

		assertTrue(facade.kaydet(musteri));

		// ekleme sonucu kontrol ediliyor...
		Statement st = VeritabaniBaglantisi.BAGLANTI.createStatement();
		ResultSet resultSet = st.executeQuery("SELECT * FROM musteriler");

		assertTrue(resultSet.next());

		String gelenAdSoyad = resultSet.getString(1);
		String gelenAd = resultSet.getString(2);
		String gelenSifre = resultSet.getString(3);
		String gelenKartNo = resultSet.getString(4);

		assertEquals("ad soyad", gelenAdSoyad);
		assertEquals("kullanici adi", gelenAd);
		assertEquals("sifre", gelenSifre);
		assertEquals("5555", gelenKartNo);

		// birden fazla olmamasi bekleniyor
		assertFalse(resultSet.next());

	}

	public void testMusteriAra() throws SQLException {

		// Yeni musteri ekleniyor...
		testMusteriEkleme();

		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();
		// eklenen musteri araniyor.

		PO musteri = new Musteri("", "kullanici adi", "", "");

		Vector<PO> bulunanPo = facade.ara(musteri);

		PO po = bulunanPo.elementAt(0);

		assertEquals("ad soyad", ((Musteri) po).getAdSoyad());
		assertEquals("sifre", ((Musteri) po).getParola());
		assertEquals("5555", ((Musteri) po).getKrediKartNo());

	}

	public void testMusteriGuncelle() throws SQLException {

		testMusteriEkleme();

		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();
		// guncellenecek musteri araniyor..

		PO musteri = new Musteri("", "kullanici adi", "", "");
		Musteri yeniMusteri = new Musteri();
		yeniMusteri.setID("kullaniciAdi");
		yeniMusteri.setAdSoyad("");
		yeniMusteri.setParola("");
		yeniMusteri.setKrediKartNo("");

		boolean bulunanMusteri = facade.guncelle(musteri, yeniMusteri);
		assertTrue(bulunanMusteri);

	}

	public void testMusteriSil() throws SQLException {
		
		testMusteriEkleme();

		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();
		
		PO musteri = new Musteri("","kullanici adi","","");
		
		boolean silinenMusteri = facade.sil(musteri);
		
		assertTrue(silinenMusteri);
		
		VeritabaniBaglantisi.baglantiKapat();
	}

}
