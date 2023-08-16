package com.finanzas.trabajoFinanzas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="money")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",nullable = false,length = 50)
    private String name;
}
