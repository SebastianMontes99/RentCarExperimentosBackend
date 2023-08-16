package com.finanzas.trabajoFinanzas.repositories;

import com.finanzas.trabajoFinanzas.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersRepository extends JpaRepository<Users,Long>{
    Users findByUsername(String username);


}
