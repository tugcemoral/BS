package gui;

import javax.swing.table.TableModel;

public class GUIFabrika {
	
	public TableModel getInstanceTableModel(TabloVeGostericiPanelCesitleri tabloModelBelirleyici){
		
		if (tabloModelBelirleyici.equals(TabloVeGostericiPanelCesitleri.DVD_PANEL)) {
			return new DVDTabloModeli();
		} else if (tabloModelBelirleyici.equals(TabloVeGostericiPanelCesitleri.MUSTERI_PANEL)) {
			return new MusteriTabloModeli();
		} else if (tabloModelBelirleyici.equals(TabloVeGostericiPanelCesitleri.PERSONEL_PANEL)) {
			return new PersonelTabloModeli();
		}
		return null;
		
	}
	
	public POGosterici getInstancePoGosterici(TabloVeGostericiPanelCesitleri gostericiBelirleyici){
		
		if (gostericiBelirleyici.equals(TabloVeGostericiPanelCesitleri.DVD_PANEL)) {
			return new DVDGosterici();
		} else if (gostericiBelirleyici.equals(TabloVeGostericiPanelCesitleri.MUSTERI_PANEL)) {
			return new MusteriGosterici();
		} else if (gostericiBelirleyici.equals(TabloVeGostericiPanelCesitleri.PERSONEL_PANEL)) {
			return new PersonelGosterici();
		}
		return null;
	}
	
}
