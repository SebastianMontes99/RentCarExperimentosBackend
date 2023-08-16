package com.finanzas.trabajoFinanzas.services.impl;

import com.finanzas.trabajoFinanzas.entities.Users;
import com.finanzas.trabajoFinanzas.exception.UsernameAlreadyExistsException;
import com.finanzas.trabajoFinanzas.repositories.IUsersRepository;
import com.finanzas.trabajoFinanzas.services.IUsersService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersServiceImpl implements IUsersService {

    private final IUsersRepository usersRepository;

    public UsersServiceImpl(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public Users save(Users users) throws Exception {
        return usersRepository.save(users);
    }

    @Override
    public void delete(Long id) throws Exception {
    usersRepository.deleteById(id);
    }

    @Override
    public List<Users> getAll() throws Exception {
        return usersRepository.findAll();
    }

    @Override
    public Optional<Users> getById(Long id) throws Exception {
        return usersRepository.findById(id);
    }
    @Override
    public Users login(String username, String password) {
        Users user = usersRepository.findByUsername(username); // Busca al usuario por su nombre de usuario
        if (user != null && user.getPassword().equals(password)) { // Verifica si el usuario existe y si la contraseña es correcta
            return user; // Retorna el usuario si las credenciales son válidas
        } else {
            return null; // Retorna null si las credenciales son inválidas
        }
    }

    @Override
    public void registerUser(Users user) {
        // Verificar si ya existe un usuario con el mismo nombre de usuario
        Users existingUser = usersRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new UsernameAlreadyExistsException("El nombre de usuario ya está en uso.");
        }
        // Si no existe, guardar el usuario en la base de datos
        usersRepository.save(user);
    }
}
