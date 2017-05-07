package pl.sternik.rcz.weekend.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


public class gra {

    private Long numerKatalogowy;
	
	private Long PEGI;
	
	private String nazwa;
	private String nosnik;
	private BigDecimal cenaNabycia;
	private Date dataNabycia;
	private String wydawca;
	private Status status;

	public static gra producegra(Long numerKatalogowy, String wydawca, Long PEGI, String nosnik, String nazwa,
			Date dataNabycia, BigDecimal cenaNabycia, Status status) {
		gra m = new gra();
		m.numerKatalogowy = numerKatalogowy;
		m.wydawca = wydawca;
		m.PEGI = PEGI;
		m.nazwa = nazwa;
		m.nosnik = nosnik;
		m.cenaNabycia = cenaNabycia;
		m.dataNabycia = dataNabycia;
		m.status = status;
		return m;
	}

	public Long getNumerKatalogowy() {
		return numerKatalogowy;
	}

	public Long getPEGI() {
		return PEGI;
	}

	public String getNazwa() {
		return nazwa;
	}

	public String getNosnik() {
		return nosnik;
	}

	public BigDecimal getCenaNabycia() {
		return cenaNabycia;
	}

	public Date getDataNabycia() {
		return dataNabycia;
	}

	public String getWydawca() {
		return wydawca;
	}

	public Status getStatus() {
		return status;
	}

	public void setNumerKatalogowy(Long numerKatalogowy) {
		this.numerKatalogowy = numerKatalogowy;
	}

	public void setPEGI(Long PEGI) {
		this.PEGI = PEGI;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public void setNosnik(String nosnik) {
		this.nosnik = nosnik;
	}

	public void setCenaNabycia(BigDecimal cenaNabycia) {
		this.cenaNabycia = cenaNabycia;
	}

	public void setDataNabycia(Date dataNabycia) {
		this.dataNabycia = dataNabycia;
	}

	public void setWydawca(String wydawca) {
		this.wydawca = wydawca;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nazwa == null) ? 0 : nazwa.hashCode());
		result = prime * result + ((cenaNabycia == null) ? 0 : cenaNabycia.hashCode());
		result = prime * result + ((dataNabycia == null) ? 0 : dataNabycia.hashCode());
		result = prime * result + ((wydawca == null) ? 0 : wydawca.hashCode());
		result = prime * result + ((PEGI == null) ? 0 : PEGI.hashCode());
		result = prime * result + ((numerKatalogowy == null) ? 0 : numerKatalogowy.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((nosnik == null) ? 0 : nosnik.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		gra other = (gra) obj;
		if (nazwa == null) {
			if (other.nazwa != null)
				return false;
		} else if (!nazwa.equals(other.nazwa))
			return false;
		if (cenaNabycia == null) {
			if (other.cenaNabycia != null)
				return false;
		} else if (!cenaNabycia.equals(other.cenaNabycia))
			return false;
		if (dataNabycia == null) {
			if (other.dataNabycia != null)
				return false;
		} else if (!dataNabycia.equals(other.dataNabycia))
			return false;
		if (wydawca == null) {
			if (other.wydawca != null)
				return false;
		} else if (!wydawca.equals(other.wydawca))
			return false;
		if (PEGI == null) {
			if (other.PEGI != null)
				return false;
		} else if (!PEGI.equals(other.PEGI))
			return false;
		if (numerKatalogowy == null) {
			if (other.numerKatalogowy != null)
				return false;
		} else if (!numerKatalogowy.equals(other.numerKatalogowy))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (nosnik == null) {
			if (other.nosnik != null)
				return false;
		} else if (!nosnik.equals(other.nosnik))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "gra [numerKatalogowy=" + numerKatalogowy + ", PEGI=" + PEGI + ", Nazwa=" + nazwa + ", nosnik="
				+ nosnik + ", cenaNabycia=" + cenaNabycia + ", dataNabycia=" + dataNabycia + ", wydawca="
				+ wydawca + ", status=" + status + "]";
	}

}
