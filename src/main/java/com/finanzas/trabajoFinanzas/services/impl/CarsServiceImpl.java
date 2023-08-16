package com.finanzas.trabajoFinanzas.services.impl;


import com.finanzas.trabajoFinanzas.entities.Cars;
import com.finanzas.trabajoFinanzas.entities.Users;
import com.finanzas.trabajoFinanzas.exception.UsernameAlreadyExistsException;
import com.finanzas.trabajoFinanzas.repositories.ICarsRepository;
import com.finanzas.trabajoFinanzas.repositories.IUsersRepository;
import com.finanzas.trabajoFinanzas.services.ICarsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarsServiceImpl implements ICarsService {

    private final ICarsRepository carsRepository;
    private final IUsersRepository usersRepository;
    public CarsServiceImpl(ICarsRepository carsRepository, IUsersRepository usersRepository) {
        this.carsRepository = carsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Cars save(Cars cars) throws Exception {
        return carsRepository.save(cars) ;
    }

    @Override
    public void delete(Long id) throws Exception {
    carsRepository.deleteById(id);
    }

    @Override
    public List<Cars> getAll() throws Exception {
        return carsRepository.findAll();
    }

    @Override
    public Optional<Cars> getById(Long id) throws Exception {
        return carsRepository.findById(id);
    }
    @Override
    public List<Cars> findByOwner(String owner) {
        return carsRepository.findByOwner(owner);
    }
    @Override
    public List<Cars> findByState(String state) {
        return carsRepository.findByState(state);
    }
    @Override
    public void registerCar(Cars cars) {
        // Verificar si ya existe un usuario con el mismo nombre de usuario
        Cars existingCar = carsRepository.findByPlack(cars.getPlack());
        if (existingCar != null) {
            throw new UsernameAlreadyExistsException("La placa ya está en uso.");
        }
        // Si no existe, guardar el usuario en la base de datos
        carsRepository.save(cars);
    }
    @Override
    public List<Cars> getCarsByState(String state) {
        return carsRepository.findByState(state);
    }

    @Override
    public List<Cars> getCarsByRenter(String renter) {
        return carsRepository.findCarsByRenter(renter);
    }
}
