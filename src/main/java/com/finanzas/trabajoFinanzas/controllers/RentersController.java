package com.finanzas.trabajoFinanzas.controllers;


import com.finanzas.trabajoFinanzas.entities.Cars;
import com.finanzas.trabajoFinanzas.entities.Renters;
import com.finanzas.trabajoFinanzas.entities.Users;
import com.finanzas.trabajoFinanzas.exception.UsernameAlreadyExistsException;
import com.finanzas.trabajoFinanzas.services.IRentersService;
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
@RequestMapping("/renters")
@CrossOrigin
public class RentersController {
    public RentersController(IRentersService rentersService) {
        this.rentersService = rentersService;
    }
    private IRentersService rentersService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Renters>> findAll(){
        try {
            List<Renters> renters= rentersService.getAll();
            if(renters.size()>0)
                return new ResponseEntity<>(renters, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Renters> findById(@PathVariable("id")Long id){
        try{
            Optional<Renters> renters=rentersService.getById(id);
            if(!renters.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(renters.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/renters/insert")
    public ResponseEntity<Map<String, Object>> saveRenters(@RequestBody Renters renters) {
        Map<String, Object> response = new HashMap<>();
        try {
            Renters savedRenters = rentersService.save(renters);
            response.put("success", true);
            response.put("data", savedRenters);
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
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Renters> updateUsers(@PathVariable("id")Long id,@Valid @RequestBody Renters renters){
        try{
            Optional<Renters> rentersUpdate= rentersService.getById(id);
            if(!rentersUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            renters.setId(id);
            rentersService.save(renters);
            return new ResponseEntity<>(renters,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> deleteUser(@PathVariable("id")Long id){
        try{
            Optional<Renters> rentersDelete=rentersService.getById(id);
            if(!rentersDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            rentersService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/renter/{renter}")
    public ResponseEntity<List<Renters>> getRentByRenter(@PathVariable String renter) {
        List<Renters> renters = rentersService.getRentByRenter(renter);
        return new ResponseEntity<>(renters, HttpStatus.OK);
    }
}
