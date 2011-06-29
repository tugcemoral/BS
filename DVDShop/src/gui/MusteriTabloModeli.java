package gui;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import domain.Musteri;
import domain.PO;

public class MusteriTabloModeli extends DefaultTableModel implements TableModel, POTabloModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1048918565148445265L;

	Vector<PO> musteriler;

	String[] kolonAdlari = new String[] { "Ad Soyad", "Kullanýcý Adý",
			"Parola", "Kredi Kart No" };

	public MusteriTabloModeli() {
		super();
		musteriler = new Vector<PO>();
	}

	public int getColumnCount() {
		return 4;
	}

	public String getColumnName(int col) {
		
		return kolonAdlari[col];
	}

	public Object getValueAt(int row, int col) {
		Musteri musteri= (Musteri)this.musteriler.get(row);
		if (col == 0)
			return musteri.getAdSoyad();
		else if (col == 1)
			return musteri.getKullaniciAdi();
		else if (col == 2)
			return musteri.getParola();
		else if (col == 3)
			return musteri.getKrediKartNo();
		return "bos";
	}
	
	public int getRowCount() {
		if(musteriler != null)
			return musteriler.size();
		else
			return 0;
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	public void addPO(PO yeniMusteri){
		musteriler.addElement(yeniMusteri);
	}

	public void removeAllPo() {
		this.musteriler.removeAllElements();
	}

	public void addPO(Vector<PO> bulunanMusteriler) {
		this.musteriler.addAll(bulunanMusteriler);
	}

	public void removePO(PO guncellenecekMusteri) {
		this.musteriler.removeElement(guncellenecekMusteri);
		
	}

	public PO getPO(int selectedRow) {
		return this.musteriler.get(selectedRow);
	}
}
