package gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.VeriYoneticisiOnCephe;
import domain.Musteri;
import domain.PO;
import domain.Personel;

public class GirisGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JTextField jtxtKullaniciAdi = null;

	private JPasswordField jpswrdSifre = null;

	private JLabel jLblKullaniciAdi = null;

	private JLabel jLblParola = null;

	private JButton jBtnGiris = null;

	/**
	 * This is the default constructor
	 */
	public GirisGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLocation(new Point(250, 250));
		this.setSize(290, 185);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("DVD Shop Giris");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLblParola = new JLabel();
			jLblParola.setBounds(new Rectangle(16, 60, 107, 30));
			jLblParola.setText("Sifre: ");
			jLblKullaniciAdi = new JLabel();
			jLblKullaniciAdi.setBounds(new Rectangle(14, 14, 107, 28));
			jLblKullaniciAdi.setText("Kullanýcý Adý: ");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJtxtKullaniciAdi(), null);
			jContentPane.add(getJpswrdSifre(), null);
			jContentPane.add(jLblKullaniciAdi, null);
			jContentPane.add(jLblParola, null);
			jContentPane.add(getJBtnGiris(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jtxtKullaniciAdi
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtKullaniciAdi() {
		if (jtxtKullaniciAdi == null) {
			jtxtKullaniciAdi = new JTextField();
			jtxtKullaniciAdi.setBounds(new Rectangle(151, 14, 120, 30));
		}
		return jtxtKullaniciAdi;
	}

	/**
	 * This method initializes jpswrdSifre
	 * 
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJpswrdSifre() {
		if (jpswrdSifre == null) {
			jpswrdSifre = new JPasswordField();
			jpswrdSifre.setBounds(new Rectangle(151, 61, 120, 30));
		}
		return jpswrdSifre;
	}

	/**
	 * This method initializes jBtnGiris
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJBtnGiris() {
		if (jBtnGiris == null) {
			jBtnGiris = new JButton();
			jBtnGiris.setBounds(new Rectangle(90, 104, 108, 32));
			jBtnGiris.setText("Giris");
			jBtnGiris.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					sistemeGir();
				}
			});
		}
		return jBtnGiris;
	}

	private void sistemeGir() {
		// aranacak PO nesnesi
		PO po;

		// Ekrandaki girdiler aliniyor.
		String kullaniciAdi = jtxtKullaniciAdi.getText();
		String parola = new String(jpswrdSifre.getPassword());

		// Ýlk olarak musteri olma ihtimaline bakilacak.
		po = new Musteri("", kullaniciAdi, parola, "");
		VeriYoneticisiOnCephe facade = new VeriYoneticisiOnCephe();
		Vector<PO> bulunanlar = facade.ara(po);
		if (bulunanlar.size() == 0 || bulunanlar == null) {
			// Musteri degilse personel mi diye bakilacak.
			po = new Personel("", kullaniciAdi, parola);
			bulunanlar = facade.ara(po);
			if (bulunanlar.size() == 0 || bulunanlar == null) {
				JOptionPane.showMessageDialog(null, "Yeniden deneyin", "Hata!",
						JOptionPane.ERROR_MESSAGE);
				jtxtKullaniciAdi.setText("");
				jpswrdSifre.setText("");
			} else {
				// bulunduysa tek eleman gelir. anahtar sahaya gore ariyoruz.
				Personel bulunanPersonel = (Personel) bulunanlar.elementAt(0);
				if (bulunanPersonel.getParola().equals(parola)) {
					AnaPencere gui = new AnaPencere(
							TabloVeGostericiPanelCesitleri.PERSONEL_PANEL,
							DugmePanelCesitleri.PERSONEL_DUGME_PANELI);
					gui.setVisible(true);
					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Yeniden deneyin...",
							"HATA!!!", JOptionPane.ERROR_MESSAGE);
					jtxtKullaniciAdi.setText("");
					jpswrdSifre.setText("");
				}
			}
		} else {
			Musteri bulunanMusteri = (Musteri) bulunanlar.elementAt(0);
			if (bulunanMusteri.getParola().equals(parola)) {
				AnaPencere gui = new AnaPencere(
						TabloVeGostericiPanelCesitleri.MUSTERI_PANEL,
						DugmePanelCesitleri.MUSTERI_DUGME_PANELI);
				gui.setVisible(true);
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Yeniden deneyin...",
						"HATA!!!", JOptionPane.ERROR_MESSAGE);
				jtxtKullaniciAdi.setText("");
				jpswrdSifre.setText("");
			}
		}
	}

	public static void main(String[] args) {
		new GirisGUI().setVisible(true);
	}

} // @jve:decl-index=0:visual-constraint="10,10"
