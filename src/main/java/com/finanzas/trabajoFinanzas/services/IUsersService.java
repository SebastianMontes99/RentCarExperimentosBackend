package com.finanzas.trabajoFinanzas.services;

import com.finanzas.trabajoFinanzas.entities.Users;

public interface IUsersService extends CrudService<Users> {
    Users login(String username, String password);
    void registerUser(Users user);

}
