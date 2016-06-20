package by.epam.task8.entity;

import java.io.Serializable;
import java.sql.Date;

public class Discount implements Serializable{
    private int id;
    private String name;
    private int amount_of_discount;
    private Date start_date_time;
    private Date finish_date_time;

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

    public int getAmount_of_discount() {
        return amount_of_discount;
    }

    public void setAmount_of_discount(int amount_of_discount) {
        this.amount_of_discount = amount_of_discount;
    }

    public Date getStart_date_time() {
        return start_date_time;
    }

    public void setStart_date_time(Date start_date_time) {
        this.start_date_time = start_date_time;
    }

    public Date getFinish_date_time() {
        return finish_date_time;
    }

    public void setFinish_date_time(Date finish_date_time) {
        this.finish_date_time = finish_date_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (id != discount.id) return false;
        if (amount_of_discount != discount.amount_of_discount) return false;
        if (name != null ? !name.equals(discount.name) : discount.name != null) return false;
        if (start_date_time != null ? !start_date_time.equals(discount.start_date_time) : discount.start_date_time != null)
            return false;
        return finish_date_time != null ? finish_date_time.equals(discount.finish_date_time) : discount.finish_date_time == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + amount_of_discount;
        result = 31 * result + (start_date_time != null ? start_date_time.hashCode() : 0);
        result = 31 * result + (finish_date_time != null ? finish_date_time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DiscountDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount_of_discount=" + amount_of_discount +
                ", start_date_time=" + start_date_time +
                ", finish_date_time=" + finish_date_time +
                '}';
    }
}
