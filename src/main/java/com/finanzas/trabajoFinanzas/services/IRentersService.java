package com.finanzas.trabajoFinanzas.services;

import com.finanzas.trabajoFinanzas.entities.Cars;
import com.finanzas.trabajoFinanzas.entities.Renters;

import java.util.List;


public interface IRentersService extends CrudService<Renters>{
    List<Renters> getRentByRenter(String renter);
}
