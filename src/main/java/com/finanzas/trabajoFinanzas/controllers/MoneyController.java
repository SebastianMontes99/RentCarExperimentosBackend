package com.finanzas.trabajoFinanzas.controllers;

import com.finanzas.trabajoFinanzas.entities.Money;
import com.finanzas.trabajoFinanzas.entities.Users;
import com.finanzas.trabajoFinanzas.services.IMoneyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/money")
@CrossOrigin
public class MoneyController {
    private IMoneyService moneyService;

    public MoneyController(IMoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Money>> findAll(){
        try {
            List<Money> money= moneyService.getAll();
            if(money.size()>0)
                return new ResponseEntity<>(money, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Money> findById(@PathVariable("id")Long id){
            try{
                Optional<Money> money=moneyService.getById(id);
                if(!money.isPresent())
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(money.get(),HttpStatus.OK);
            }catch (Exception ex){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Money> insertMoney(@Valid @RequestBody Money money){
        try{
            Money moneyNew= moneyService.save(money);
            return ResponseEntity.status(HttpStatus.CREATED).body(moneyNew);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Money> updateMoney(@PathVariable("id")Long id,@Valid @RequestBody Money money){
        try{
            Optional<Money> moneyUpdate= moneyService.getById(id);
            if(!moneyUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            money.setId(id);
            moneyService.save(money);
            return new ResponseEntity<>(money,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Money> deleteMoney(@PathVariable("id")Long id){
        try{
            Optional<Money> moneyDelete=moneyService.getById(id);
            if(!moneyDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            moneyService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
