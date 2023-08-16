package com.finanzas.trabajoFinanzas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username",nullable = false,length = 50)
    private String username;
    @Column(name="password",nullable = false,length = 50)
    private String password;
    @Column(name="email",nullable = false,length = 50)
    private String email;

    @ManyToOne
    @JoinColumn(name = "money_id") //nombre de la columna que se usará como clave foránea
    private Money money;
}
