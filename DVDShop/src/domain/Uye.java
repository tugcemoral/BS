package domain;

abstract public class Uye implements PO{
	
	String adSoyad;
	String kullaniciAdi;
	String parola;
	
	public Uye() {
		
	}
	
	public Uye(String adSoyad, String kullaniciAdi, String parola) {
		super();
		this.adSoyad = adSoyad;
		this.kullaniciAdi = kullaniciAdi;
		this.parola = parola;
	}
	
	public String getAdSoyad() {
		return adSoyad;
	}
	
	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}
	
	public String getKullaniciAdi() {
		return kullaniciAdi;
	}
	
	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getParola() {
		return parola;
	}
	
	public void setParola(String sifre) {
		this.parola = sifre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((adSoyad == null) ? 0 : adSoyad.hashCode());
		result = PRIME * result + ((kullaniciAdi == null) ? 0 : kullaniciAdi.hashCode());
		result = PRIME * result + ((parola == null) ? 0 : parola.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		final Uye other = (Uye) obj;
		if (adSoyad == null) {
			if (other.adSoyad != null)
				return false;
		} else if (!adSoyad.equals(other.adSoyad))
			return false;
		if (kullaniciAdi == null) {
			if (other.kullaniciAdi != null)
				return false;
		} else if (!kullaniciAdi.equals(other.kullaniciAdi))
			return false;
		else
			return true;
		if (parola == null) {
			if (other.parola != null)
				return false;
		} else if (!parola.equals(other.parola))
			return false;
		return true;
	}
	
}
