package com.unicesumar.service;

import com.unicesumar.entities.Product;
import com.unicesumar.repository.ProductRepository;
import com.unicesumar.view.ProductView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ProductService {
    ProductView productView;
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, ProductView productView) {
        this.productRepository = productRepository;
        this.productView = productView;
    }

    public void listProducts(){
        System.out.println("Listar Produtos");
        System.out.println("|------------------------------------|");
        List<Product> products = productRepository.findAll();
        products.forEach(productView::showProduct);
//        products.forEach(System.out::println); //Antigo
//                    for(Product product : products){
//                        productView.showProduct(product);
//                    }
    }

    public void registerProduct(){
        System.out.println("Cadastrar Produto");
        productRepository.save(new Product("Teste", 10));
        productRepository.save(new Product("Computador", 3000));
    }

    public List<Product> searchProductById(){
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        System.out.println();

        while(products.isEmpty()){
            List<UUID> ids = new ArrayList<>();
            System.out.println("Digite os IDs dos produtos (separados por \",\"):");

            System.out.print("ID: ");
            String input = sc.nextLine().trim();

            boolean inputValido = true;

            for (String idString : input.split(",")) {
                try {
                    UUID id = UUID.fromString(idString.trim());
                    ids.add(id);
                } catch (IllegalArgumentException e) {
                    System.out.println("ID inválido encontrado: " + idString.trim());
                    inputValido = false;
                    break;
                }
            }

            if (!inputValido) {
                System.out.println("Tente novamente. Apenas IDs no formato UUID são permitidos.");
                continue;
            }

            products = productRepository.findByIds(ids);

            if (products.size() < ids.size()) {
                System.out.println("Um ou mais produtos não foram encontrados no banco.");
                System.out.println("Tente novamente inserindo apenas IDs válidos e existentes.");
                products.clear(); // força repetir
            }
        }

        System.out.println("\nProdutos encontrados com sucesso!");
        System.out.println("|------------------------------------|");
        products.forEach(productView::showProduct);

        return products;
    }
}
