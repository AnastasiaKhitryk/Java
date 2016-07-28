package by.epam.task8.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Order extends Model implements Serializable{
    private static final long serialVersionUID = 1L;

    private int user_id;
    private BigDecimal total_price;
    private String status;
    private String delivery_type;
    private String address;
    private String phone;
    private String payment_type;
    private Date date;
    private String order_detail;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
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

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(String order_detail) {
        this.order_detail = order_detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (user_id != order.user_id) return false;
        if (total_price != null ? !total_price.equals(order.total_price) : order.total_price != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        if (delivery_type != null ? !delivery_type.equals(order.delivery_type) : order.delivery_type != null)
            return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        if (phone != null ? !phone.equals(order.phone) : order.phone != null) return false;
        if (payment_type != null ? !payment_type.equals(order.payment_type) : order.payment_type != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        return order_detail != null ? order_detail.equals(order.order_detail) : order.order_detail == null;

    }

    @Override
    public int hashCode() {
        int result = user_id;
        result = 31 * result + (total_price != null ? total_price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (delivery_type != null ? delivery_type.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (payment_type != null ? payment_type.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (order_detail != null ? order_detail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user_id=" + user_id +
                ", total_price=" + total_price +
                ", status='" + status + '\'' +
                ", delivery_type='" + delivery_type + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", payment_type='" + payment_type + '\'' +
                ", date=" + date +
                ", order_detail='" + order_detail + '\'' +
                '}';
    }
}
