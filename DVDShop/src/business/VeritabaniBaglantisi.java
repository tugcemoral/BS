package business;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author Tayfun Gokmen Halac && Tugcem Oral
 * 
 * Bu sinif java mysql veritabaný baglantisini ve baglanti ile ilgili iþlemleri
 * tanýmlar. Veritabanýnda test adlý sýnýfa baglanir.
 * 
 */
public class VeritabaniBaglantisi {

	public static void main(String[] args) {

	}

	public static void baglantiAc() {
		try {
			// program baslatildiginda veritabani ile baglanti
			// kuruluyor ve program kapanana kadar acik kaliyor.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			VeritabaniBaglantisi.BAGLANTI = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "etmen123");

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}

	}

	public static void baglantiKapat() {
		try {

			// program bittiginde baglanti kapatiliyor.
			VeritabaniBaglantisi.BAGLANTI.close();

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		} // catch
	}

	// baglantiyi saglayan degisken.
	public static Connection BAGLANTI;

}
