package tr.ege;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import junit.framework.TestCase;
import business.VeriYoneticisiOnCephe;
import business.VeritabaniBaglantisi;
import business.VeritabaniniIlklendir;
import domain.PO;
import domain.Personel;

public class PersonelTest extends TestCase {

	
	public void testPersonelEkleme() throws SQLException{
		
		//Veritabani tablolari dusurulup tekrar aciliyor
		VeritabaniniIlklendir.tablolariHazirla();		
		//Form icindeki baglanti noktalari tek oldugundan ve giris
		//Formundan alindiginda burada ilklendirilmesi gerekiyor.
		VeritabaniBaglantisi.baglantiAc();
		
		//gui sadece facade i tanýyor. veri yoneticileriyle iliskisi bulunmuyor.
		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();
		//aranacak kalici nesne olusturuluyor. (persistent object)
		PO personel = new  Personel("ad soyad", "kullanici adi", "sifre");
		
		//eklendigi test ediliyor.
		assertTrue(facade.kaydet(personel));
		
		//ekleme sonucu kontrol ediliyor...
		Statement st = VeritabaniBaglantisi.BAGLANTI.createStatement();
		ResultSet resultSet = st.executeQuery("SELECT * FROM personel");
		//eklenen personel kontrol ediliyor..
		assertTrue(resultSet.next());
		
		String gelenAdSoyad = resultSet.getString(1);
		String gelenAd = resultSet.getString(2);
		String gelenSifre = resultSet.getString(3); 
		
		assertEquals("ad soyad", gelenAdSoyad);
		assertEquals("kullanici adi", gelenAd);
		assertEquals("sifre", gelenSifre);
		//Veritabani ilklendiricisi xx adinda bir personel eklediginden
		//veritabaninda toplam kayit sayisinin iki olmasini bekleniyor.
		//"kullanici adi" alfabetik sirada xx den once oldugu icin ikinci 
		//xx in olup olmadigi burda kontrol ediliyor.
		assertTrue(resultSet.next());
		
	}
	
	public void testPersonelAra() throws SQLException {
		
		testPersonelEkleme();
		
		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();
		PO personel = new Personel("","xx","");
		
		//'xx' personeli araniyor...	
		Vector<PO> bulunanPo = facade.ara(personel);
		PO po = bulunanPo.elementAt(0);
		assertEquals("xx", ((Personel)po).getAdSoyad());
		assertEquals("xx", ((Personel)po).getParola());
		
		//kullanici adi personeli araniyor
		((Personel)personel).setKullaniciAdi("kullanici adi");
		bulunanPo = facade.ara(personel);
		po = bulunanPo.elementAt(0);
		assertEquals("ad soyad", ((Personel)po).getAdSoyad());
		assertEquals("sifre", ((Personel)po).getParola());
		
	}
	
	public void testPersonelGuncelle() throws SQLException {
		
		testPersonelEkleme();
		
		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();
		Personel arananPersonel = new Personel("", "xx", "");
		
		Vector<PO> bulunan = facade.ara(arananPersonel);
		Personel guncellenecekPersonel = (Personel) bulunan.elementAt(0);
		
		Personel yeniPersonel = new Personel();
		yeniPersonel.setAdSoyad("ad soyad");
		yeniPersonel.setID("");
		yeniPersonel.setParola("");
		
		assertTrue(facade.guncelle(guncellenecekPersonel, yeniPersonel));
		
	}
	
	public void testPersonelSil() throws SQLException{

		testPersonelEkleme();
		
		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();
		PO silinecekPersonel = new Personel("", "kullanici adi", "");
		
		//'kullanici adi' personeli silinecek.
		assertTrue(facade.sil(silinecekPersonel));
		
		VeritabaniBaglantisi.baglantiKapat();
	}
}
