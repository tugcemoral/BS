package business;
import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.Statement;


public class VeritabaniniIlklendir {
	public static void main(String[] args) {
		tablolariHazirla();
	}

	public static void tablolariHazirla() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conTest = DriverManager.getConnection(
					"jdbc:mysql://localhost/test", "root", "etmen123");
			
			Statement komut = (Statement)conTest.createStatement();
			
			komut.executeUpdate("DROP TABLE IF EXISTS personel");
			komut.executeUpdate("DROP TABLE IF EXISTS musteriler");
			komut.executeUpdate("DROP TABLE IF EXISTS dvdler");
			komut.executeUpdate("CREATE TABLE personel (adsoyad char(50), username char(50) NOT NULL PRIMARY KEY, password char(50));");
			komut.executeUpdate("CREATE TABLE musteriler (adsoyad char(50),username char(50)  NOT NULL PRIMARY KEY, password char(50), kartNo char(50));");
			komut.executeUpdate("CREATE TABLE dvdler (filmAdi char(50), yonetmen char(30), fiyat smallint, barkod char(10)  NOT NULL PRIMARY KEY,  sirket char(30), adet smallint);");
			komut.executeUpdate("INSERT INTO personel (adsoyad, username, password) VALUES ('xx', 'xx', 'xx');");
			
			komut.close();
			
		} catch (Exception err) {
			System.out.println("Driver Error " + err.getMessage());
		}
	}
}
