package domain;

/**
 * 
 * @author Tugcem Oral, Tayfun Gokmen Halac
 *Musteri nesnesinin sýnýfý. PO interface inden türer.
 *
 */
public class Musteri extends Uye {

	String krediKartNo;

	public Musteri() {

	}

	public Musteri(String adSoyad, String kullaniciAdi, String sifre,
			String krediKartNo) {
		super(adSoyad, kullaniciAdi, sifre);
		this.krediKartNo = krediKartNo;
	}

	public String getKrediKartNo() {
		return krediKartNo;
	}

	public void setKrediKartNo(String krediKartNo) {
		this.krediKartNo = krediKartNo;
	}

	public String getID() {
		return super.kullaniciAdi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result
				+ ((krediKartNo == null) ? 0 : krediKartNo.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Musteri other = (Musteri) obj;
		if (krediKartNo == null) {
			if (other.krediKartNo != null)
				return false;
		} else if (!krediKartNo.equals(other.krediKartNo))
			return false;
		return true;
	}

	public void setID(String id) {
		
		this.kullaniciAdi = id;
		
	}

}
