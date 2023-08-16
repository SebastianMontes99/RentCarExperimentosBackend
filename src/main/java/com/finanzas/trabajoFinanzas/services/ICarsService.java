package com.finanzas.trabajoFinanzas.services;

import com.finanzas.trabajoFinanzas.entities.Cars;
import com.finanzas.trabajoFinanzas.entities.Users;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface ICarsService extends CrudService<Cars>{
    void registerCar(Cars cars);
    List<Cars> findByOwner(String owner);
    List<Cars>findByState(String state);
    List<Cars> getCarsByState(String state);
    List<Cars> getCarsByRenter(String renter);

}
