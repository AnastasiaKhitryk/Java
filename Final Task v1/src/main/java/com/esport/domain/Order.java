package main.java.com.esport.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int userId;
	private BigDecimal totalPrice;
	private OrderStatus status;
	private OrderDeliveryType deliveryType;
	private OrderPaymentType paymentType;
	private String address;
	private String phone;
	private Date date;
	private String orderDetail;
	
	private Order(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public OrderDeliveryType getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(OrderDeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}
	public OrderPaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(OrderPaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
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
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", totalPrice=" + totalPrice + ", status=" + status
				+ ", deliveryType=" + deliveryType + ", paymentType=" + paymentType + ", address=" + address
				+ ", phone=" + phone + ", date=" + date + ", orderDetail=" + orderDetail + "]";
	}
	
	
}
