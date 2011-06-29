package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Musteri;
import domain.PO;

public class MusteriGosterici extends JPanel implements POGosterici {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Metin Kutulari
	private JTextField jtxtAdSoyad;

	private JTextField jtxtKullaniciAdi;

	private JTextField jtxtParola;

	private JTextField jtxtKrediKartNo;

	public MusteriGosterici() {
		super();
		musteriGosterimPaneliHazirla();
	}

	private void musteriGosterimPaneliHazirla() {

		// Musteri Gösterimi
		this.setLayout(new GridLayout(4, 2));
		// etiketler tanýmlanýyor...
		JLabel jlblAdSoyad = new JLabel("Ad Soyad");
		JLabel jlblKullaniciAdi = new JLabel("Kullanici Adi");
		JLabel jlblParola = new JLabel("Parola");
		JLabel jlblKrediKartNo = new JLabel("Kredi Kart No");

		// Metin kutularý ayarlanýyor...

		jtxtAdSoyad = new JTextField();
		jtxtKullaniciAdi = new JTextField();
		jtxtParola = new JTextField();
		jtxtKrediKartNo = new JTextField();
		// Panele Ekleniyor...
		this.add(jlblAdSoyad);
		this.add(jtxtAdSoyad);

		this.add(jlblKullaniciAdi);
		this.add(jtxtKullaniciAdi);

		this.add(jlblParola);
		this.add(jtxtParola);

		this.add(jlblKrediKartNo);
		this.add(jtxtKrediKartNo);

	}

	public PO arayuzdenPOYarat() {

		Musteri musteri = new Musteri();
		if (!this.jtxtAdSoyad.getText().trim().equals(""))
			musteri.setAdSoyad(this.jtxtAdSoyad.getText());
		if (!this.jtxtKullaniciAdi.getText().trim().equals(""))
			musteri.setKullaniciAdi(jtxtKullaniciAdi.getText());
		if (!this.jtxtParola.getText().trim().equals(""))
			musteri.setParola(jtxtParola.getText());
		if (!this.jtxtKrediKartNo.getText().trim().equals(""))
			musteri.setKrediKartNo(jtxtKrediKartNo.getText());
		return (PO) musteri;
	}

	public void setGuiData(PO po) {

		Musteri musteri = (Musteri) po;

		this.jtxtAdSoyad.setText(musteri.getAdSoyad());
		this.jtxtKullaniciAdi.setText(musteri.getKullaniciAdi());
		this.jtxtParola.setText(musteri.getParola());
		this.jtxtKrediKartNo.setText(musteri.getKrediKartNo());
	}

	public void resetGuiData() {

		this.jtxtAdSoyad.setText("");
		this.jtxtKullaniciAdi.setText("");
		this.jtxtParola.setText("");
		this.jtxtKrediKartNo.setText("");

	}

}
