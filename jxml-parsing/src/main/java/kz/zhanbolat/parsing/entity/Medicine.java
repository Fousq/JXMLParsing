package kz.zhanbolat.parsing.entity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Medicine {
	private String name;
	private Pharm pharm;
	private String group;
	private List<String> analogs;
	private List<Version> versions;
	
	public Medicine() {
		
	}
	
	public Medicine(String name, Pharm pharm, String group, 
					List<String> analogs, List<Version> versions) {
		this.name = name;
		this.pharm = pharm;
		this.group = group;
		this.analogs = analogs;
		this.versions = versions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pharm getPharm() {
		return pharm;
	}

	public void setPharm(Pharm pharm) {
		this.pharm = pharm;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<String> getAnalogs() {
		return Collections.unmodifiableList(analogs);
	}

	public void setAnalogs(List<String> analogs) {
		this.analogs = analogs;
	}

	public List<Version> getVersions() {
		return Collections.unmodifiableList(versions);
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Medicine [name=");
		builder.append(name);
		builder.append(", pharm=");
		builder.append(pharm);
		builder.append(", group=");
		builder.append(group);
		builder.append(", analogs=");
		builder.append(analogs);
		builder.append(", versions=");
		builder.append(versions);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(analogs, group, name, pharm, versions);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicine other = (Medicine) obj;
		return Objects.equals(analogs, other.analogs) && Objects.equals(group, other.group)
				&& Objects.equals(name, other.name) && Objects.equals(pharm, other.pharm)
				&& Objects.equals(versions, other.versions);
	}
	
}
