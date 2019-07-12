package kz.zhanbolat.parsing.entity;

import java.util.Objects;

public class Dosage {
	private String units;
	private int measuring;
	private String frequency;
	
	public Dosage() {
		
	}
	
	public Dosage(String units, int measuring, String frequency) {
		this.units = units;
		this.measuring = measuring;
		this.frequency = frequency;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public int getMeasuring() {
		return measuring;
	}

	public void setMeasuring(int measuring) {
		this.measuring = measuring;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dosage [measuring=");
		builder.append(measuring);
		builder.append(" ");
		builder.append(units);
		builder.append(", frequency=");
		builder.append(frequency);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(frequency, measuring, units);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dosage other = (Dosage) obj;
		return Objects.equals(frequency, other.frequency) && measuring == other.measuring
				&& Objects.equals(units, other.units);
	}
	
}
