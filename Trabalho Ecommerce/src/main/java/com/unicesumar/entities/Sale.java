package com.unicesumar.entities;

import com.unicesumar.paymentMethods.PaymentMethod;
import com.unicesumar.paymentMethods.PaymentType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Sale extends Entity{
    private User user;
    private List<Product> products;
    private PaymentType paymentType;
    private LocalDateTime paymentDate;

    public Sale(UUID uuid, User user, List<Product> products, PaymentType paymentType, LocalDateTime paymentDate) {
        super(uuid);
        this.user = user;
        this.products = products;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
    }

    public Sale(User user, List<Product> products, PaymentType paymentType, LocalDateTime paymentDate) {
        this.user = user;
        this.products = products;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
