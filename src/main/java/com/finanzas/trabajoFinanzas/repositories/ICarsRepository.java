package com.finanzas.trabajoFinanzas.repositories;

import com.finanzas.trabajoFinanzas.entities.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICarsRepository extends JpaRepository<Cars,Long>{
Cars findByPlack(String plack);
    List<Cars> findByOwner(String owner);
    List<Cars>findByState(String state);
    List<Cars> findCarsByRenter(String renter);

}
