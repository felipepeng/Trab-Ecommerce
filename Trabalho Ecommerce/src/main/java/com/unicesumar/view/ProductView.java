package com.unicesumar.view;

import com.unicesumar.entities.Product;

public class ProductView {

    public void showProduct(Product product) {
        System.out.println("ID: " + product.getUuid().toString());
        System.out.println("Nome: " + product.getName());
        System.out.println("Preço: " + product.getPrice());
        System.out.println("|------------------------------------|");
    }
}
