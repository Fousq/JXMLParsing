package kz.zhanbolat.parsing.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Certificate {
	public static final String DATE_FORMAT = "MM-yyyy";
	private static SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
	private int id;
	private Date dateOfIssue;
	private Date exparetionDate;
	private String RegisterOrg;
	
	public Certificate() {
		
	}
	
	public Certificate(int id, Date dateOfIssue, Date exparetionDate, 
						String RegisterOrg) {
		this.id = id;
		this.dateOfIssue = dateOfIssue;
		this.exparetionDate = exparetionDate;
		this.RegisterOrg = RegisterOrg;
	}
	
	public Certificate(int id, String dateOfIssue, String exparetionDate,
						String RegisterOrg) throws ParseException {
		this.id = id;
		this.dateOfIssue = format.parse(dateOfIssue);
		this.exparetionDate = format.parse(exparetionDate);
		this.RegisterOrg = RegisterOrg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	
	public void setDateOfIssue(String dateOfIssue) throws ParseException {
		this.dateOfIssue = format.parse(dateOfIssue);
	}

	public Date getExparetionDate() {
		return exparetionDate;
	}

	public void setExparetionDate(Date exparetionDate) {
		this.exparetionDate = exparetionDate;
	}
	
	public void setExparetionDate(String exparetionDate) 
													throws ParseException {
		this.exparetionDate = format.parse(exparetionDate);
	}
	
	public String getRegisterOrg() {
		return RegisterOrg;
	}

	public void setRegisterOrg(String registerOrg) {
		RegisterOrg = registerOrg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Certificate [id=");
		builder.append(id);
		builder.append(", dateOfIssue=");
		builder.append(format.format(dateOfIssue));
		builder.append(", exparetionDate=");
		builder.append(format.format(exparetionDate));
		builder.append(", RegisterOrg=");
		builder.append(RegisterOrg);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(RegisterOrg, dateOfIssue, exparetionDate, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Certificate other = (Certificate) obj;
		return Objects.equals(RegisterOrg, other.RegisterOrg) && Objects.equals(dateOfIssue, other.dateOfIssue)
				&& Objects.equals(exparetionDate, other.exparetionDate) && id == other.id;
	}
	
}
