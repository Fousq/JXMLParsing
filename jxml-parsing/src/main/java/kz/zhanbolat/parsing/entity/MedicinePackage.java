package kz.zhanbolat.parsing.entity;

import java.util.Objects;

public class MedicinePackage {
	private String type;
	private int quantity;
	private int price;
	
	public MedicinePackage() {
		
	}
	
	public MedicinePackage(String type, int quantity, int price) {
		this.type = type;
		this.quantity = quantity;
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MedicinePackage [type=");
		builder.append(type);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(price, quantity, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicinePackage other = (MedicinePackage) obj;
		return price == other.price && quantity == other.quantity && Objects.equals(type, other.type);
	}
	
}
