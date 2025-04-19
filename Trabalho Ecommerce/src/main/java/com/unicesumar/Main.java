package com.unicesumar;

import com.unicesumar.entities.Product;
import com.unicesumar.entities.User;
import com.unicesumar.repository.ProductRepository;
import com.unicesumar.repository.SaleRepository;
import com.unicesumar.repository.UserRepository;
import com.unicesumar.service.ProductService;
import com.unicesumar.service.SaleService;
import com.unicesumar.service.UserService;
import com.unicesumar.view.IndexView;
import com.unicesumar.view.ProductView;
import com.unicesumar.view.SaleView;
import com.unicesumar.view.UserView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Repositorys
        ProductRepository productRepository = null;
        UserRepository userRepository = null;
        SaleRepository saleRepository = null;
        Connection conn = null;


        // Parâmetros de conexão
        String url = "jdbc:sqlite:database.sqlite";

        // Tentativa de conexão
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                productRepository = new ProductRepository(conn);
                userRepository = new UserRepository(conn);
                saleRepository = new SaleRepository(conn);
            } else {
                System.out.println("Falha na conexão.");
                System.exit(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            System.exit(1);
        }

        //Views
        IndexView indexView = new IndexView();
        ProductView productView = new ProductView();
        UserView userView = new UserView();
        SaleView saleView = new SaleView();
        //Services
        UserService userService = new UserService(userRepository, userView);
        ProductService productService = new ProductService(productRepository, productView);
        SaleService saleService = new SaleService(saleRepository, saleView);

        //Scanner
        Scanner scanner = new Scanner(System.in);

        int option;

        do {
            indexView.showMainMenu();
            //option = scanner.nextInt(); Virou a de baixo
            option = indexView.getMainMenuOption();

            switch (option) {
                case 1:
                    productService.registerProduct();
                    break;
                case 2:
                    productService.listProducts();
                    break;
                case 3:
                    productService.searchProductById();
                    break;
                case 4:
                    userService.registerUsers();
                    break;
                case 5:
                    userService.showUsers();
                    break;
                case 6:
                    userService.searchUserByEmail();
                    break;
                case 7: //Realizar Compra
                    User client = userService.searchUserByEmail();
                    List<Product> products = productService.searchProductById();
                    saleService.realizarSale(client, products);
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");
            }
        } while (option != 8);

        scanner.close(); //Fecha o Scanner
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
