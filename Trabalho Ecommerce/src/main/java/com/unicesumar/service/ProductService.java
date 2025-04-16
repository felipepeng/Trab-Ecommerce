package com.unicesumar.service;

import com.unicesumar.entities.Product;
import com.unicesumar.repository.ProductRepository;
import com.unicesumar.view.ProductView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ProductService {
    List<Product> products;
    ProductView productView;
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, ProductView productView) {
        this.productRepository = productRepository;
        this.productView = productView;
    }

    public void listProducts(){
        System.out.println("Listar Produtos");
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

    public void searchProductById(){
        Scanner sc = new Scanner(System.in);
        List<UUID> ids = new ArrayList<>();

        System.out.println("Digite os IDs dos produtos (digite 'fim' para terminar):");

        //Laço de repetição para coletar os Ids
        while (true) {
            System.out.print("ID: ");
            String input = sc.nextLine().trim();

            //Condição de saída do laço
            if (input.equalsIgnoreCase("fim")) {
                break;
            }

            try {
                UUID id = UUID.fromString(input);
                ids.add(id); //Adiciona o ID a lista
            } catch (IllegalArgumentException e) {
                System.out.println("ID inválido! Tente novamente.");
            }
        }



        //Procura os Produtos com esses Ids no banco e adiciona a lista Products
        List<Product> products = productRepository.findByIds(ids);

        System.out.println("|------------------------------------|");

        //Printa na tela os produtos correspondentes da lista
        products.forEach(productView::showProduct);
    }
}
