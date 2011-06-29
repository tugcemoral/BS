package gui;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import domain.Dvd;
import domain.PO;

public class DVDTabloModeli extends DefaultTableModel implements TableModel,
		POTabloModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Vector<PO> dvdler;

	String[] kolonAdlari = new String[] { "Ad", "Yönetmen", "Þirket", "Barkod",
			"Fiyat", "Adet" };

	public DVDTabloModeli() {
		super();
		dvdler = new Vector<PO>();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public String getColumnName(int col) {
		return kolonAdlari[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Dvd dvd = (Dvd) this.dvdler.get(row);
		if (col == 0)
			return dvd.getUrunAdi();
		else if (col == 1)
			return dvd.getYonetmen();
		else if (col == 2)
			return dvd.getSirket();
		else if (col == 3)
			return dvd.getID();
		else if (col == 4)
			return dvd.getFiyat();
		else if (col == 5)
			return dvd.getAdet();
		return "bos";
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public int getRowCount() {
		if (dvdler != null)
			return dvdler.size();
		else
			return 0;
	}

	public void addPO(Vector<PO> dvdListesi) {
		this.dvdler.addAll(dvdListesi);
	}

	public void addPO(PO po) {
		dvdler.addElement(po);
	}
	
	public void removeAllPo() {
		this.dvdler.removeAllElements();
	}

	public PO getPO(int selectedRow) {
		return this.dvdler.get(selectedRow);
	}

	public void removePO(PO silinecek) {
		dvdler.removeElement(silinecek);
	}
}
