package com.trycatch_vishal.fooddelivery;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Date;

@Entity(tableName = "order_history")
public class OrderHistory {

    @PrimaryKey(autoGenerate = true)
    private long orderId;
    private List<POJOSTATERS> items;
    private double totalPrice;
    private Date orderDate;

    public OrderHistory(long orderId, List<POJOSTATERS> items, double totalPrice, Date orderDate) {
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<POJOSTATERS> getItems() {
        return items;
    }

    public void setItems(List<POJOSTATERS> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
