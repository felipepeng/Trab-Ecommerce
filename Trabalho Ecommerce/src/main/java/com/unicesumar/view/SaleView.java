package com.unicesumar.view;

import com.unicesumar.entities.Product;
import com.unicesumar.entities.Sale;
import com.unicesumar.paymentMethods.PaymentType;

import java.util.Scanner;

public class SaleView {

    Scanner sc = new Scanner(System.in);

    public PaymentType getPayment() {
        PaymentType payment = null;

        while (payment == null) {
            System.out.println("Escolha a forma de pagamento:");
            for (PaymentType type : PaymentType.values()) {
                System.out.println("- " + type.name());
            }

            System.out.print("Digite a forma de pagamento: ");
            String input = sc.nextLine().trim().toUpperCase();

            try {
                payment = PaymentType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Forma de pagamento inválida. Tente novamente.");
            }
        }

        return payment;
    }

    public void sayError(String error) {
        switch (error) {
            case "T":
                System.out.println("Tipo de pagamento inválido.");
                break;
            case "P":
                System.out.println("Produto não selecionado.");
                break;
            case "C":
                System.out.println("Cliente inválido.");
                break;
            default:
                System.out.println("Erro desconhecido.");
        }
    }

    public void showSale(Sale sale, double totalPrice) {
        System.out.println("Venda realizada com sucesso!");
        System.out.println("Cliente: " + sale.getUser().getName());
        System.out.println("Produtos: ");
        for (Product product : sale.getProducts()) {
            System.out.println("- " + product.getName() + " (R$ " + product.getPrice() + ")");
        }
        System.out.println("Total da venda: " + totalPrice);
        System.out.println("Forma de pagamento: " + sale.getPaymentType().name());
        System.out.println("Data da venda: " + sale.getPaymentDate());
    }

}
