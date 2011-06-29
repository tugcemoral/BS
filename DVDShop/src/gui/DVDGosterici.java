package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Dvd;
import domain.PO;

public class DVDGosterici extends JPanel implements POGosterici {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Metin Kutulari
	private JTextField jtxtAd;

	private JTextField jtxtYonetmen;

	private JTextField jtxtBarkod;

	private JTextField jtxtFiyat;

	private JTextField jtxtSirket;

	private JTextField jtxtAdet;

	public DVDGosterici() {
		super();
		dvdGosterimPaneliHazirla();
	}

	private void dvdGosterimPaneliHazirla() {
		// DVD Gösterimi
		this.setLayout(new GridLayout(6, 2));
		// etiketler tanýmlanýyor...
		JLabel jlblAd = new JLabel("Ad");
		JLabel jlblYonetmen = new JLabel("Yönetmen");
		JLabel jlblBarkod = new JLabel("Barkod");
		JLabel jlblFiyat = new JLabel("Fiyat");
		JLabel jlblSirket = new JLabel("Þirket");
		JLabel jlblAdet = new JLabel("Adet");

		// Metin kutularý ayarlanýyor...

		jtxtAd = new JTextField();
		jtxtYonetmen = new JTextField();
		jtxtBarkod = new JTextField();
		jtxtFiyat = new JTextField();
		jtxtSirket = new JTextField();
		jtxtAdet = new JTextField();
		// Panele Ekleniyor...
		this.add(jlblAd);
		this.add(jtxtAd);

		this.add(jlblSirket);
		this.add(jtxtSirket);

		this.add(jlblYonetmen);
		this.add(jtxtYonetmen);

		this.add(jlblBarkod);
		this.add(jtxtBarkod);

		this.add(jlblFiyat);
		this.add(jtxtFiyat);

		this.add(jlblAdet);
		this.add(jtxtAdet);
	}

	public PO arayuzdenPOYarat() {

		Dvd dvd = new Dvd();
		if (!this.jtxtAdet.getText().trim().equals(""))
			dvd.setAdet(Integer.parseInt(this.jtxtAdet.getText()));
		if (!this.jtxtFiyat.getText().trim().equals(""))
			dvd.setFiyat(Integer.parseInt(this.jtxtFiyat.getText()));
		if (!this.jtxtBarkod.getText().trim().equals(""))
			dvd.setID(this.jtxtBarkod.getText());
		if (!this.jtxtSirket.getText().trim().equals(""))
			dvd.setSirket(this.jtxtSirket.getText());
		if (!this.jtxtYonetmen.getText().trim().equals(""))
			dvd.setYonetmen(this.jtxtYonetmen.getText());
		if (!this.jtxtAd.getText().trim().equals(""))
			dvd.setUrunAdi(this.jtxtAd.getText());
		return (PO) dvd;
	}

	public void setGuiData(PO po) {
		Dvd dvd = (Dvd) po;
		this.jtxtAd.setText(dvd.getUrunAdi());
		if (dvd.getAdet() == null)
			this.jtxtAdet.setText("");
		else
			this.jtxtAdet.setText("" + dvd.getAdet());
		this.jtxtBarkod.setText(dvd.getID());
		if (dvd.getFiyat() == null)
			this.jtxtFiyat.setText("");
		else
			this.jtxtFiyat.setText("" + dvd.getFiyat());
		this.jtxtSirket.setText(dvd.getSirket());
		this.jtxtYonetmen.setText(dvd.getYonetmen());
	}

	public void resetGuiData() {
		//metin kutulari temizleniyor... 
		this.jtxtAd.setText("");
		this.jtxtAdet.setText("");
		this.jtxtBarkod.setText("");
		this.jtxtFiyat.setText("");
		this.jtxtSirket.setText("");
		this.jtxtYonetmen.setText("");
	}

}
