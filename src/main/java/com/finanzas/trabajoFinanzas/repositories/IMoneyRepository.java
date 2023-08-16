package com.finanzas.trabajoFinanzas.repositories;

import com.finanzas.trabajoFinanzas.entities.Money;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMoneyRepository extends JpaRepository<Money,Long> {
}
