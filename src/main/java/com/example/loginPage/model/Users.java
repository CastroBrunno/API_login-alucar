package com.example.loginPage.model;

import com.example.loginPage.dto.UsersRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="usuarios")
@Entity(name="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "Id")
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    public Users (UsersRequestDTO data){
        this.nome = data.nome();
        this.email = data.email();
        this.password = data.password();
    }

    // public void setPassword(String password) {
    //     throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }
}


