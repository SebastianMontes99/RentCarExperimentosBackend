package com.finanzas.trabajoFinanzas.services.impl;

import com.finanzas.trabajoFinanzas.entities.Cars;
import com.finanzas.trabajoFinanzas.entities.Renters;
import com.finanzas.trabajoFinanzas.repositories.IRentersRepository;
import com.finanzas.trabajoFinanzas.services.IRentersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RentersServiceImpl implements IRentersService {
    private final IRentersRepository rentersRepository;

    public RentersServiceImpl(IRentersRepository rentersRepository) {
        this.rentersRepository = rentersRepository;
    }

    @Override
    public Renters save(Renters renters) throws Exception {
        if (renters.getOwner().equals(renters.getRenter())) {
            throw new Exception("El owner y el renter no pueden ser iguales");
        }
        return rentersRepository.save(renters);
    }

    @Override
    public void delete(Long id) throws Exception {
    rentersRepository.deleteById(id);
    }

    @Override
    public List<Renters> getAll() throws Exception {
        return rentersRepository.findAll();
    }

    @Override
    public Optional<Renters> getById(Long id) throws Exception {
        return rentersRepository.findById(id);
    }

    @Override
    public List<Renters> getRentByRenter(String renter) {
        return rentersRepository.findRentByRenter(renter);
    }
}
