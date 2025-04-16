package com.unicesumar.entities;

import com.unicesumar.paymentMethods.PaymentMethod;
import com.unicesumar.paymentMethods.PaymentType;

import java.util.List;
import java.util.UUID;

public class Sale extends Entity{
    private User user;
    private List<Product> products;
    private PaymentType paymentType;
    private double totalAmount;

    public Sale(UUID uuid, PaymentType paymentType, List<Product> products, double totalAmount, User user) {
        super(uuid);
        this.paymentType = paymentType;
        this.products = products;
        this.totalAmount = totalAmount;
        this.user = user;
    }

    public Sale(PaymentType paymentType, List<Product> products, double totalAmount, User user) {
        this.paymentType = paymentType;
        this.products = products;
        this.totalAmount = totalAmount;
        this.user = user;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
