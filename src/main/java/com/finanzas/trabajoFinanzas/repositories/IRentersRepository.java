package com.finanzas.trabajoFinanzas.repositories;

import com.finanzas.trabajoFinanzas.entities.Renters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRentersRepository extends JpaRepository<Renters,Long>{
    List<Renters> findRentByRenter(String renter);

}
