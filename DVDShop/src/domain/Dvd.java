package domain;

/**
 * 
 * @author Tugcem Oral, Tayfun Gokmen Halac
 *Bu sýnýf Dvd nesnesinin sýnýfýdýr. PO interface inden türer.
 *
 */
public class Dvd implements PO {

	private String yonetmen;

	private String sirket;

	private String ID;

	private String urunAdi;

	private Integer fiyat;

	private Integer adet;

	public Dvd() {

	}

	public Dvd(String id, String urunAdi, Integer fiyat, Integer adet,
			String yonetmen, String sirket) {
		super();

		this.yonetmen = yonetmen;
		this.sirket = sirket;
		this.ID = id;
		this.urunAdi = urunAdi;
		this.fiyat = fiyat;
		this.adet = adet;
	}

	public String getSirket() {
		return sirket;
	}

	public void setSirket(String sirket) {
		this.sirket = sirket;
	}

	public String getYonetmen() {
		return yonetmen;
	}

	public void setYonetmen(String yonetmen) {
		this.yonetmen = yonetmen;
	}

	public Integer getAdet() {
		return adet;
	}

	public void setAdet(Integer adet) {
		this.adet = adet;
	}

	public Integer getFiyat() {
		return fiyat;
	}

	public void setFiyat(Integer fiyat) {
		this.fiyat = fiyat;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		this.ID = id;
	}

	public String getUrunAdi() {
		return urunAdi;
	}

	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((ID == null) ? 0 : ID.hashCode());
		result = PRIME * result + ((adet == null) ? 0 : adet.hashCode());
		result = PRIME * result + ((fiyat == null) ? 0 : fiyat.hashCode());
		result = PRIME * result + ((sirket == null) ? 0 : sirket.hashCode());
		result = PRIME * result + ((urunAdi == null) ? 0 : urunAdi.hashCode());
		result = PRIME * result
				+ ((yonetmen == null) ? 0 : yonetmen.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Dvd other = (Dvd) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		else
			return true;
		if (adet == null) {
			if (other.adet != null)
				return false;
		} else if (!adet.equals(other.adet))
			return false;
		if (fiyat == null) {
			if (other.fiyat != null)
				return false;
		} else if (!fiyat.equals(other.fiyat))
			return false;
		if (sirket == null) {
			if (other.sirket != null)
				return false;
		} else if (!sirket.equals(other.sirket))
			return false;
		if (urunAdi == null) {
			if (other.urunAdi != null)
				return false;
		} else if (!urunAdi.equals(other.urunAdi))
			return false;
		if (yonetmen == null) {
			if (other.yonetmen != null)
				return false;
		} else if (!yonetmen.equals(other.yonetmen))
			return false;
		return true;
	}

}
