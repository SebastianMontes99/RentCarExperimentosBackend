package com.finanzas.trabajoFinanzas.controllers;

import com.finanzas.trabajoFinanzas.entities.Cars;
import com.finanzas.trabajoFinanzas.entities.Money;
import com.finanzas.trabajoFinanzas.entities.Users;
import com.finanzas.trabajoFinanzas.exception.UsernameAlreadyExistsException;
import com.finanzas.trabajoFinanzas.services.ICarsService;
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
@RequestMapping("/cars")
@CrossOrigin
public class CarsController {

    private ICarsService carsService;

    public CarsController(ICarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cars>> findAll(){
        try {
            List<Cars> cars= carsService.getAll();
            if(cars.size()>0)
                return new ResponseEntity<>(cars, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cars> updateCars(@PathVariable("id")Long id,@Valid @RequestBody Cars cars){
        try{
            Optional<Cars> carsUpdate= carsService.getById(id);
            if(!carsUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            cars.setId(id);
            carsService.save(cars);
            return new ResponseEntity<>(cars,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{owner}")
    public ResponseEntity<List<Cars>> findByOwner(@PathVariable String owner) {
        List<Cars> cars = carsService.findByOwner(owner);
        return new ResponseEntity<List<Cars>>(cars, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerCar(@RequestBody Cars cars) {
        Map<String, Object> response = new HashMap<>();
        try {
            carsService.registerCar(cars);
            response.put("success", true);
            response.put("message", "Carro registrado exitosamente.");
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
   @GetMapping(value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cars> findById(@PathVariable("id")Long id){
        try{
            Optional<Cars> cars=carsService.getById(id);
            System.out.println(id);
            if(!cars.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(cars.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Cars>> getCarsByState(@PathVariable String state) {
        List<Cars> cars = carsService.getCarsByState(state);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/renter/{renter}")
    public ResponseEntity<List<Cars>> getCarsByRenter(@PathVariable String renter) {
        List<Cars> cars = carsService.getCarsByRenter(renter);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
