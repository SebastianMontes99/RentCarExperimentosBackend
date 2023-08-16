package com.finanzas.trabajoFinanzas.controllers;

import com.finanzas.trabajoFinanzas.entities.Users;
import com.finanzas.trabajoFinanzas.exception.UsernameAlreadyExistsException;
import com.finanzas.trabajoFinanzas.services.IUsersService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {
    private IUsersService usersService;

    public UsersController(IUsersService usersService) {
        this.usersService = usersService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> findAll(){
        try {
            List<Users> users= usersService.getAll();
            if(users.size()>0)
                return new ResponseEntity<>(users, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> findById(@PathVariable("id")Long id){
        try{
            Optional<Users> users=usersService.getById(id);
            if(!users.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(users.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> insertUsers(@Valid @RequestBody Users users){
        try{
            Users usersNew= usersService.save(users);
            return ResponseEntity.status(HttpStatus.CREATED).body(usersNew);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> updateUsers(@PathVariable("id")Long id,@Valid @RequestBody Users users){
        try{
            Optional<Users> usersUpdate= usersService.getById(id);
            if(!usersUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            users.setId(id);
            usersService.save(users);
            return new ResponseEntity<>(users,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> deleteUser(@PathVariable("id")Long id){
        try{
            Optional<Users> usersDelete=usersService.getById(id);
            if(!usersDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            usersService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> login(@RequestBody Users users, HttpSession session) {
        try {
            Users user = usersService.login(users.getUsername(), users.getPassword()); // Llama al método login de IUsersService
            if (user != null) {
                session.setAttribute("user", user); // Guarda el usuario en la sesión HTTP
                System.out.println(user);
                return new ResponseEntity<>(user, HttpStatus.OK); // Retorna el usuario si las credenciales son válidas
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Retorna el código de estado HTTP 401 (Unauthorized) si las credenciales son inválidas
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna el código de estado HTTP 500 (Internal Server Error) si ocurre un error en el servidor
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Users user) {
        Map<String, Object> response = new HashMap<>();
        try {
            usersService.registerUser(user);
            response.put("success", true);
            response.put("message", "Usuario registrado exitosamente.");
            return ResponseEntity.ok(response);
        } catch (UsernameAlreadyExistsException ex) {
            response.put("success", false);
            response.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception ex) {
            response.put("success", false);
            response.put("message", "Error interno del servidor.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/userSession") 
    public ResponseEntity<Users> getUser(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
