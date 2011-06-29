package gui;

import java.util.Vector;

import domain.PO;

public interface POTabloModel {

	public void addPO(PO yeniMusteri);

	public void removeAllPo();

	public void addPO(Vector<PO> bulunanMusteriler);

	public void removePO(PO guncellenecekMusteri);

	public PO getPO(int selectedRow);
	
}
