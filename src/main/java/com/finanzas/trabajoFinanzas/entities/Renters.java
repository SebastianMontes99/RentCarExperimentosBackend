package com.finanzas.trabajoFinanzas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="renters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Renters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "owner", nullable = false, length = 50)
    private String owner;
    @Column(name = "renter", nullable = false, length = 50)
    private String renter;
    @Column(name = "dateInit", nullable = false, length = 50)
    private Date dateInit;
    @Column(name = "dateFinal", nullable = false, length = 50)
    private Date dateFinal;
    @Column(name = "priceFinal", nullable = false, length = 50)
    private int priceFinal;
    @Column(name="brand",nullable = false,length = 50)
    private String brand;
    @Column(name="modelCar",nullable = false,length = 50)
    private String modelCar;
    @Column(name="plack",nullable = false,length = 50)
    private String plack;
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
