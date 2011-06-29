package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.PO;
import domain.Personel;

public class PersonelGosterici extends JPanel implements POGosterici {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Metin Kutulari
	private JTextField jtxtAdSoyad;

	private JTextField jtxtKullaniciAdi;

	private JTextField jtxtParola;

	public PersonelGosterici() {
		super();
		personelGosterimPaneliHazirla();
	}

	private void personelGosterimPaneliHazirla() {
		// DVD Gösterimi
		this.setLayout(new GridLayout(3, 2));
		// etiketler tanýmlanýyor...
		JLabel jlblAdSoyad = new JLabel("Ad Soyad");
		JLabel jlblKullaniciAdi = new JLabel("Kullanici Adi");
		JLabel jlblParola = new JLabel("Parola");

		// Metin kutularý ayarlanýyor...

		jtxtAdSoyad = new JTextField();
		jtxtKullaniciAdi = new JTextField();
		jtxtParola = new JTextField();

		// Panele Ekleniyor...
		this.add(jlblAdSoyad);
		this.add(jtxtAdSoyad);

		this.add(jlblKullaniciAdi);
		this.add(jtxtKullaniciAdi);

		this.add(jlblParola);
		this.add(jtxtParola);
	}

	public PO arayuzdenPOYarat() {
		Personel personel = new Personel();
		if (!this.jtxtAdSoyad.getText().trim().equals(""))
			personel.setAdSoyad(this.jtxtAdSoyad.getText());
		if (!this.jtxtKullaniciAdi.getText().trim().equals(""))
			personel.setKullaniciAdi(this.jtxtKullaniciAdi.getText());
		if (!this.jtxtParola.getText().trim().equals(""))
			personel.setParola(this.jtxtParola.getText());

		return (PO) personel;
	}

	public void setGuiData(PO po) {
		Personel personel = (Personel) po;
		this.jtxtAdSoyad.setText(personel.getAdSoyad());
		this.jtxtKullaniciAdi.setText(personel.getID());
		this.jtxtParola.setText(personel.getParola());
	}

	public void resetGuiData() {

		this.jtxtAdSoyad.setText("");
		this.jtxtKullaniciAdi.setText("");
		this.jtxtParola.setText("");

	}

}
