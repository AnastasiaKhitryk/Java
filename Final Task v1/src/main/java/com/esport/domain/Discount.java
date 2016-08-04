package main.java.com.esport.domain;

import java.io.Serializable;
import java.util.Date;

public class Discount implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private int amountOfDiscount;
	private Date startDate;
	private Date finishDate;
	
	public Discount(){}

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

	public int getAmountOfDiscount() {
		return amountOfDiscount;
	}

	public void setAmountOfDiscount(int amountOfDiscount) {
		this.amountOfDiscount = amountOfDiscount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
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
		Discount other = (Discount) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Discount [id=" + id + ", name=" + name + ", amountOfDiscount=" + amountOfDiscount + ", startDate="
				+ startDate + ", finishDate=" + finishDate + "]";
	}

	
}
