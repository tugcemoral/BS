package business;

import java.util.Vector;

import domain.PO;

public interface VeriYoneticisi {
	
	public boolean kaydet(PO po);
	public Vector<PO> ara(PO po);
	public boolean guncelle(PO po, PO yeniPo);
	public boolean sil(PO po);
	public int satinAl(PO satilacak);
	public String sorguHazirla(PO po);
	
}
