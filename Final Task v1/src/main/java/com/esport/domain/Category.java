package main.java.com.esport.domain;

import java.io.Serializable;
import java.sql.Blob;

public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String categoryDescription;
	private Blob image;
	private int categoryParentId;
	
	public Category(){}
	
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
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public int getCategoryParentId() {
		return categoryParentId;
	}
	public void setCategoryParentId(int categoryParentId) {
		this.categoryParentId = categoryParentId;
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", categoryDescription=" + categoryDescription + ", image="
				+ image + ", categoryParentId=" + categoryParentId + "]";
	}
	
}
