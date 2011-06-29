package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;


public class AnaPencere extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//pencereye yerleþtirilen ana panel ve icinde po islemleri gerceklesen dinamikPanel. 
	private JPanel jpnlMain;
	private DinamikPOPanel jpnlDinamikPanel;

	
	/**
	 * bu sýnýfýn yapýcý metodu
	 * JFrame'i gelen numaraya gore duzenliyor
	 * girisNo 1 ise gelen Musteri 2 ise gelen personel. 
	 * 
	 */
	public AnaPencere(TabloVeGostericiPanelCesitleri tabloveGostericiTipi, DugmePanelCesitleri dugmePaneliTipi) throws HeadlessException {
		super("DVD Shop");
		initialize();
		// içerik döþeniyor...
		ilklendir(tabloveGostericiTipi, dugmePaneliTipi);
		// Dýþ görünüþ ayarlanýr...
		if (tabloveGostericiTipi.equals(TabloVeGostericiPanelCesitleri.PERSONEL_PANEL))
			this.setSize(400, 650);
		else
			this.setSize(400, 400);
		this.setVisible(true);
	}
	
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setLocation(new Point(300, 300));
        this.setTitle("DvdShop");
        this.setResizable(false);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        	public void windowClosing(java.awt.event.WindowEvent e) {
        		GirisGUI gui = new GirisGUI();
        		gui.setVisible(true);
        	}
        });
			
	}

	/**
	 * bu metod formda ilklendirme isini yapar.
	 * @param tabloVeGostericiTipi (1 ise musteri icin form, 2 ise personel icin form olusturuluyor. )
	 */
	private void ilklendir(TabloVeGostericiPanelCesitleri tabloVeGostericiTipi, DugmePanelCesitleri dugmePaneliTipi){
		
		jpnlMain = new JPanel(new BorderLayout());
		//PO islemlerini yapan dinamik panel tanimlaniyor. 
		jpnlDinamikPanel = new DinamikPOPanel(TabloVeGostericiPanelCesitleri.DVD_PANEL, dugmePaneliTipi);
		JPanel jpnlMenu = MenuHazirla();
		//diger paneller ana panele ekleniyor. 
		if (tabloVeGostericiTipi.equals(TabloVeGostericiPanelCesitleri.PERSONEL_PANEL)){
			jpnlMain.add(jpnlDinamikPanel, BorderLayout.SOUTH);
			jpnlMain.add(jpnlMenu, BorderLayout.NORTH);
		}
		else
			jpnlMain.add(jpnlDinamikPanel, BorderLayout.CENTER);
		
		
		//ana panel ekleniyor. 
		this.getContentPane().add(jpnlMain);
	}

	/**
	 * Bu metod personel icin dugmeleri hazirlar. 
	 * 
	 */
	private JPanel MenuHazirla() {
		JPanel jpnlMenu = new JPanel(new BorderLayout());
		JPanel jpnlIcPanel = new JPanel(new GridLayout(1, 3));
		jpnlMenu.add(jpnlIcPanel, BorderLayout.CENTER);
		
		//dugmeler tanimlaniyor ve ekleniyor. 
		JButton jbtnDvd =new JButton("Dvd");
		JButton jbtnMusteri =new JButton("Musteri");
		JButton jbtnPersonel =new JButton("Personel");
		
		//Dugme sorumluluklari ataniyor...
		jbtnDvd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				paneliDegistir(TabloVeGostericiPanelCesitleri.DVD_PANEL, DugmePanelCesitleri.PERSONEL_DUGME_PANELI);
			}

		});
		jbtnMusteri.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				paneliDegistir(TabloVeGostericiPanelCesitleri.MUSTERI_PANEL, DugmePanelCesitleri.PERSONEL_DUGME_PANELI);
			}

		});
		jbtnPersonel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				paneliDegistir(TabloVeGostericiPanelCesitleri.PERSONEL_PANEL, DugmePanelCesitleri.PERSONEL_DUGME_PANELI);
			}

		});
		
		jpnlIcPanel.add(jbtnDvd);
		jpnlIcPanel.add(jbtnMusteri);
		jpnlIcPanel.add(jbtnPersonel);
		
		return jpnlMenu;
	}

	/**
	 * 
	 * @param tabloveGostericiTipi (musteri, personel veya dvd panellerinin olusturulup ekrana yansitir. )
	 */
	private void paneliDegistir(TabloVeGostericiPanelCesitleri tabloveGostericiTipi, DugmePanelCesitleri dugmePaneliTipi) {
		jpnlMain.remove(jpnlDinamikPanel);
		jpnlDinamikPanel = new DinamikPOPanel(tabloveGostericiTipi, dugmePaneliTipi);
		jpnlMain.add(jpnlDinamikPanel);
		jpnlMain.updateUI();
	}
}
