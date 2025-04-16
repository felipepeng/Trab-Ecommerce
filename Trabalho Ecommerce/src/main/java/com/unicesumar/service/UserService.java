package com.unicesumar.service;

import com.unicesumar.entities.User;
import com.unicesumar.repository.UserRepository;
import com.unicesumar.view.UserView;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserService {

    UserRepository userRepository;
    UserView userView;
    List<User> users;

    public UserService(UserRepository userRepository, UserView userView) {
        this.userRepository = userRepository;
        this.userView = userView;
    }

    public void showUsers(){
        System.out.println("Listar Usuários");
        List<User> users = userRepository.findAll(); //Cria a lista de users
        users.forEach(userView::showUser); //Mostra as informações de cada user através da classe UserView e metodo showUser
    }

    public void registerUsers(){
        System.out.println("Cadastrar Usuário");
        userRepository.save(new User("Felipe", UUID.randomUUID().toString().concat("felpinho@123.com"), "123"));
        userRepository.save(new User("Rafael",  UUID.randomUUID().toString().concat("lamoburguini@456.br"), "456"));
        userRepository.save(new User("Pedro", UUID.randomUUID().toString().concat("pedro@alvaro.org.br.com.batman"), "789"));
        //userRepository.save(new User("Peng", "peng114@gmail.com", "12345678"));
    }

    public void searchUserByEmail(){
        System.out.print("Email: ");
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            User u = user.get();
            System.out.println("Usuário encontrado:");
            System.out.println("UUID: " + u.getUuid());
            System.out.println("Nome: " + u.getName());
            System.out.println("Email: " + u.getEmail());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

}
