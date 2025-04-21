package com.unicesumar.service;

import com.unicesumar.entities.Product;
import com.unicesumar.entities.Sale;
import com.unicesumar.entities.User;
import com.unicesumar.paymentMethods.PaymentType;
import com.unicesumar.repository.SaleRepository;
import com.unicesumar.view.SaleView;

import java.time.LocalDateTime;
import java.util.List;

public class SaleService {
    private SaleRepository saleRepository;
    private SaleView view;

    public SaleService(SaleRepository saleRepository, SaleView view) {
        this.saleRepository = saleRepository;
        this.view = view;
    }

    public void realizarSale(User client, List<Product> products) {
        PaymentType paymentType = view.getPayment();
        if (paymentType == null) {
            view.sayError("T");
            return;
        }
        if (products == null || products.isEmpty()) {
            view.sayError("P");
            return;
        }
        if (client == null) {
            view.sayError("C");
            return;
        }
        Sale sale = new Sale( client, products, paymentType, LocalDateTime.now());

        try {
            saleRepository.save(sale);
            view.showSale(sale, totalPrice(products));
        } catch (Exception e) {
            System.out.println("Erro ao realizar a venda: " + e.getMessage());
        }

    }

    public double totalPrice(List<Product> products) {
        double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
