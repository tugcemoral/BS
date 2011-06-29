package gui;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import domain.PO;
import domain.Personel;

public class PersonelTabloModeli extends DefaultTableModel implements
		TableModel, POTabloModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<PO> personeller;
	
	String[] kolonAdlari = new String[] { "AdSoyad", "Kullanici Adi", "Parola" };

	public PersonelTabloModeli() {
		super();
		personeller = new Vector<PO>();
	}

	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		if (personeller != null)
			return personeller.size();
		else
			return 0;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Personel personel = (Personel) this.personeller.get(row);
		if (col == 0)
			return personel.getAdSoyad();
		else if (col == 1)
			return personel.getKullaniciAdi();
		else if (col == 2)
			return personel.getParola();
		return "bos";
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	@Override
	public String getColumnName(int col) {
		return kolonAdlari[col];
	}

	public void addPO(PO po) {
		personeller.addElement(po);
	}

	public void addPO(Vector<PO> personelListesi) {
		this.personeller.addAll(personelListesi);

	}

	public PO getPO(int selectedRow) {
		return this.personeller.get(selectedRow);
	}

	public void removeAllPo() {
		this.personeller.removeAllElements();
	}

	public void removePO(PO silinecek) {
		personeller.removeElement(silinecek);

	}

}
