package ch.corner.lab.vault.vaultexampleweb.controller;

import ch.corner.lab.vault.vaultexampleweb.model.CreditCard;
import ch.corner.lab.vault.vaultexampleweb.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CardController {

    @Autowired(required = false)
    private CreditCardRepository carRepository;

    @GetMapping("/cards")
    public ResponseEntity<?> get(){
        List<CreditCard> all = carRepository.findAll().stream().limit(100).collect(Collectors.toList());
        return ResponseEntity.ok(all);
    }


    @GetMapping("/cards/{pan}")
    public ResponseEntity<?> generate(@PathVariable String pan){

        CreditCard creditCard = new CreditCard();
        creditCard.setPan(pan);
        carRepository.save(creditCard);
        return ResponseEntity.ok().build();
    }
}
