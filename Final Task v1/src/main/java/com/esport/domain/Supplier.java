package main.java.com.esport.domain;

import java.io.Serializable;
import java.sql.Blob;

public class Supplier implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private Blob image;
	private String supplierDescription;
	
	public Supplier(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getSupplierDescription() {
		return supplierDescription;
	}

	public void setSupplierDescription(String supplierDescription) {
		this.supplierDescription = supplierDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Supplier other = (Supplier) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", image=" + image + ", supplierDescription="
				+ supplierDescription + "]";
	}
}
