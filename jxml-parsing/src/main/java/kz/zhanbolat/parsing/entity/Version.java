package kz.zhanbolat.parsing.entity;

import java.util.Objects;

public class Version {
	private String consistency;
	private Certificate certificate;
	private MedicinePackage pack;
	private Dosage dosage;
	
	public Version() {
		
	}
	
	public Version(String consistency, Certificate certificate, 
					MedicinePackage pack, Dosage dosage) {
		this.consistency = consistency;
		this.certificate = certificate;
		this.pack = pack;
		this.dosage = dosage;
	}

	public String getConsistency() {
		return consistency;
	}

	public void setConsistency(String consistency) {
		this.consistency = consistency;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public MedicinePackage getPack() {
		return pack;
	}

	public void setPack(MedicinePackage pack) {
		this.pack = pack;
	}

	public Dosage getDosage() {
		return dosage;
	}

	public void setDosage(Dosage dosage) {
		this.dosage = dosage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Version [consistency=");
		builder.append(consistency);
		builder.append(", certificate=");
		builder.append(certificate);
		builder.append(", pack=");
		builder.append(pack);
		builder.append(", dosage=");
		builder.append(dosage);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(certificate, consistency, dosage, pack);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Version other = (Version) obj;
		return Objects.equals(certificate, other.certificate) && Objects.equals(consistency, other.consistency)
				&& Objects.equals(dosage, other.dosage) && Objects.equals(pack, other.pack);
	}
	
}
