package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business.VeriYoneticisiOnCephe;
import domain.Dvd;
import domain.PO;
import domain.POFabrikasi;

public class DinamikPOPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// kullanilan tablo ve gosterici tanimlaniyor...
	private JTable jtblMain;

	private POGosterici poGosterici;

	// guncelleme islemi icin ekrandaki ilk urunun bilgileri.
	private PO guncellenecek;

	public DinamikPOPanel(TabloVeGostericiPanelCesitleri tabloveGostericiTipi,
			DugmePanelCesitleri dugmePaneliTipi) throws HeadlessException {
		super();
		// icerik doseniyor...
		init(tabloveGostericiTipi, dugmePaneliTipi);
		// Dis gorunum ayarlaniyor...
		this.setSize(400, 400);
		this.setVisible(true);
	}

	private void init(TabloVeGostericiPanelCesitleri tabloveGostericiTipi,
			DugmePanelCesitleri dugmePaneliTipi) {

		this.setLayout(new BorderLayout());
		JPanel jpnlDugmeler = dugmePaneliHazirla(dugmePaneliTipi);
		JPanel jpnlTabloPaneli;

		// Gosterim ve Dugme panelleri hazirlaniyor...
		GUIFabrika fabrika = new GUIFabrika();
		this.poGosterici = fabrika.getInstancePoGosterici(tabloveGostericiTipi);

		jpnlTabloPaneli = tabloPaneliHazirla(tabloveGostericiTipi);

		// Kuzey panel ayarlaniyor
		JPanel jpnlKuzey = new JPanel(new BorderLayout());
		jpnlKuzey.add(jpnlDugmeler, BorderLayout.SOUTH);
		jpnlKuzey.add((JPanel) this.poGosterici, BorderLayout.CENTER);

		// Yavru paneller ana panele ekleniyor..
		this.add(jpnlKuzey, BorderLayout.NORTH);
		this.add(jpnlTabloPaneli, BorderLayout.CENTER);
	}

	private JPanel tabloPaneliHazirla(
			final TabloVeGostericiPanelCesitleri tabloTipi) {
		JPanel jpnlTabloPaneli = new JPanel(new BorderLayout());
		jpnlTabloPaneli.setBorder(BorderFactory.createLineBorder(Color.blue));

		GUIFabrika fabrika = new GUIFabrika();

		jtblMain = new JTable(fabrika.getInstanceTableModel(tabloTipi));

		jtblMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel listSelectionModel = jtblMain.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(
							ListSelectionEvent listSelectionEvent) {
						if (listSelectionEvent.getValueIsAdjusting())
							return;

						ListSelectionModel lsm = (ListSelectionModel) listSelectionEvent
								.getSource();
						if (!lsm.isSelectionEmpty()) {
							int selectedRow = lsm.getMinSelectionIndex();
							guncellenecek = ((POTabloModel) jtblMain.getModel())
									.getPO(selectedRow);
							poGosterici.setGuiData(guncellenecek);
						}
					}

				});

		jpnlTabloPaneli.add(new JScrollPane(jtblMain));
		return jpnlTabloPaneli;
	}

	private JPanel dugmePaneliHazirla(DugmePanelCesitleri dugmePaneliTipi) {
		// Dugmeler ayarlaniyor...
		JPanel jpnlDugmeler = new JPanel(new FlowLayout());
		jpnlDugmeler.setBorder(BorderFactory.createEtchedBorder(Color.GREEN,
				Color.BLACK));

		// Dugmeler tanimlaniyor..
		JButton jbtnAra = new JButton("Ara");
		JButton jbtnEkle = new JButton("Ekle");
		JButton jbtnGuncelle = new JButton("Guncelle");
		JButton jbtnSil = new JButton("Sil");
		JButton jbtnSatinAl = new JButton("Satin Al");
		JButton jbtnSifirla = new JButton("Reset");

		// Dugme sorumluluklari ataniyor...
		jbtnAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ara(poGosterici.arayuzdenPOYarat());
			}
		});
		jbtnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PO dvd = poGosterici.arayuzdenPOYarat();
				kaydet(dvd);
			}
		});
		jbtnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guncelle(guncellenecek, poGosterici.arayuzdenPOYarat());
			}
		});
		jbtnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sil(poGosterici.arayuzdenPOYarat());
			}
		});
		jbtnSatinAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				satinAl(poGosterici.arayuzdenPOYarat());
			}
		});
		jbtnSifirla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sifirla();
			}

		});

		// D��meler panele ekleniyor...
		jpnlDugmeler.add(jbtnAra);
		if (dugmePaneliTipi.equals(DugmePanelCesitleri.PERSONEL_DUGME_PANELI)) {
			jpnlDugmeler.add(jbtnEkle);
			jpnlDugmeler.add(jbtnGuncelle);
			jpnlDugmeler.add(jbtnSil);
		}
		if (dugmePaneliTipi.equals(DugmePanelCesitleri.MUSTERI_DUGME_PANELI))
			jpnlDugmeler.add(jbtnSatinAl);
		jpnlDugmeler.add(jbtnSifirla);
		return jpnlDugmeler;
	}

	private void kaydet(PO kaydedilecek) {

		if (new VeriYoneticisiOnCephe().kaydet(kaydedilecek)) {
			POTabloModel tabloModeli = (POTabloModel) this.jtblMain.getModel();
			tabloModeli.addPO(kaydedilecek);
			this.poGosterici.setGuiData(POFabrikasi.getInstance(kaydedilecek));
			jtblMain.updateUI();
			JOptionPane.showMessageDialog(this, "Kayit Basarili");
		} else
			JOptionPane.showMessageDialog(this, "Kayit Basarisiz");
	}

	@SuppressWarnings("unchecked")
	private void ara(PO po) {

		Vector bulunanlar = (new VeriYoneticisiOnCephe()).ara(po);

		if (bulunanlar != null && !bulunanlar.isEmpty()) {

			POTabloModel tabloModeli = (POTabloModel) this.jtblMain.getModel();
			tabloModeli.removeAllPo();
			tabloModeli.addPO(bulunanlar);
			jtblMain.updateUI();

		} else
			JOptionPane.showMessageDialog(this, "Aradiginiz bulunamadi! ",
					"Bilgi", JOptionPane.INFORMATION_MESSAGE);
	}

	private void guncelle(PO guncellenecek, PO yeni) {

		if (new VeriYoneticisiOnCephe().guncelle(guncellenecek, yeni)) {

			POTabloModel tabloModeli = (POTabloModel) this.jtblMain.getModel();
			tabloModeli.removePO(guncellenecek);
			tabloModeli.addPO(yeni);
			this.poGosterici.setGuiData(POFabrikasi.getInstance(guncellenecek));
			jtblMain.updateUI();
			JOptionPane.showMessageDialog(this, "Basariyla guncellendi...");
		} else
			JOptionPane.showMessageDialog(this, "Guncelleme islemi basarisiz!");

	}

	private void sil(PO silinecek) {

		if (new VeriYoneticisiOnCephe().sil(silinecek)) {

			POTabloModel tabloModeli = (POTabloModel) this.jtblMain.getModel();
			tabloModeli.removePO(silinecek);
			this.poGosterici.setGuiData(POFabrikasi.getInstance(silinecek));
			jtblMain.updateUI();
			JOptionPane.showMessageDialog(this, "Basariyla silindi...");
		} else
			JOptionPane.showMessageDialog(this, "Silme islemi basarisiz!");

	}

	private void satinAl(PO satilacak) {
		VeriYoneticisiOnCephe onCephe = new VeriYoneticisiOnCephe();
		int satisDurumu = onCephe.satinAl(satilacak);
		if (satisDurumu == 0) {

			JOptionPane.showMessageDialog(this, "Stokta yok...");
		} else if (satisDurumu == 1) {
			POTabloModel tabloModeli = (POTabloModel) this.jtblMain.getModel();
			tabloModeli.removePO(satilacak);
			tabloModeli.addPO(satilacak);
			this.poGosterici.setGuiData(POFabrikasi.getInstance(satilacak));
			jtblMain.updateUI();
			String satilacakUrunBilgisi = "Urun satildi..." + "\n"
					+ "Film Adi :" + ((Dvd) satilacak).getUrunAdi() + "\n"
					+ "Yonetmen Adi :" + ((Dvd) satilacak).getYonetmen() + "\n"
					+ "Fiyati :" + ((Dvd) satilacak).getFiyat() + "\n"
					+ "Sirket :" + ((Dvd) satilacak).getSirket();
			JOptionPane.showMessageDialog(null, satilacakUrunBilgisi,
					"Urun bilgileri", JOptionPane.INFORMATION_MESSAGE);
		} else if (satisDurumu == 2)
			JOptionPane.showMessageDialog(this, "Hata olustu!");
	}

	private void sifirla() {
		poGosterici.resetGuiData();
		// TODO tabloyu sifirlamiyor. 
	}
}
