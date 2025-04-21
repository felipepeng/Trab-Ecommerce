package com.unicesumar.view;

import java.util.Scanner;

public class IndexView {

    private Scanner scanner = new Scanner(System.in);

    public void showMainMenu(){
        System.out.println("\n---MENU---");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Listas Produtos");
        System.out.println("3 - Buscar Produto Por ID");
        System.out.println("4 - Cadastrar Usuário");
        System.out.println("5 - Listar Usuários");
        System.out.println("6 - Procurar Usuário por Email");
        System.out.println("7 - Realizar Venda");
        System.out.println("8 - Sair");
        System.out.println("Escolha uma opção: ");
    }

    public int getMainMenuOption() {
        return scanner.nextInt();
    }

}
