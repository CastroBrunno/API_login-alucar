package com.example.loginPage.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginPage.Service.UsersService;
import com.example.loginPage.dto.UsersRequestDTO;
import com.example.loginPage.model.Users;

@RestController
@RequestMapping("/auth")
public class UsersController {

    @Autowired
    private UsersService userService;

    //POST http://localhost:8080/auth/cadastrar
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody UsersRequestDTO data) {
    Users novoUsuario = new Users();
    novoUsuario.setNome(data.nome());
    novoUsuario.setEmail(data.email());
    novoUsuario.setPassword(data.password()); 

    userService.salvarUsuario(novoUsuario); 
    
    return ResponseEntity.status(201).body("Cadastrado!");
}

    //POST http://localhost:8080/auth/login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsersRequestDTO data) {
        Optional<Users> user = userService.verificarLogin(data.email(), data.password());

        System.out.println("Email recebido: " + data.email());
        if (user.isPresent()) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado. Por favor, crie uma conta.");
        }
    }
}
