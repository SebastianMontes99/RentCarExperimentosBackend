package com.finanzas.trabajoFinanzas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="brand",nullable = false,length = 50)
    private String brand;
    @Column(name="modelCar",nullable = false,length = 50)
    private String modelCar;
    @Column(name="plack",nullable = false,length = 50)
    private String plack;
    @Column(name="owner",nullable = false,length = 50)
    private String owner;
    @Column(name="renter",length = 50)
    private String renter;
    @Column(name="state",nullable = false,length = 50)
    private String state;
    @Column(name="image",nullable = false)
    private String image;
    @Column(name="year",nullable = false,length = 50)
    private String year;
    @Column(name="Km",nullable = false,length = 50)
    private String km;
    @Column(name="price",nullable = false,length = 50)
    private String price;
    @Column(name="phone",nullable = false,length = 50)
    private String phone;
    @Column(name="country",nullable = false,length = 50)
    private String country;
    @Column(name="city",nullable = false,length = 50)
    private String city;
}
