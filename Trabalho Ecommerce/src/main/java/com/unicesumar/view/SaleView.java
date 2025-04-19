package com.unicesumar.view;

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

}
