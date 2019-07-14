package kz.zhanbolat.parsing.entity;

import java.util.Objects;

public class Pharm {
	private String name;
	private String country;
	
	public Pharm() {
		
	}
	
	public Pharm(String name, String country) {
		this.name = name;
		this.country = country;
	}
	
	public Pharm(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pharm [name=");
		builder.append(name);
		builder.append(", country=");
		builder.append(country);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pharm other = (Pharm) obj;
		return Objects.equals(country, other.country) 
				&& Objects.equals(name, other.name);
	}
	
}
