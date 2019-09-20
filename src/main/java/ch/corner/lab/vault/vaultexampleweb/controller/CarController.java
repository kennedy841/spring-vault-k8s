package ch.corner.lab.vault.vaultexampleweb.controller;

import ch.corner.lab.vault.vaultexampleweb.model.Car;
import ch.corner.lab.vault.vaultexampleweb.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {

    @Autowired(required = false)
    private CarRepository carRepository;

    @GetMapping("/cars")
    public ResponseEntity<?> get(){
        List<Car> all = carRepository.findAll().stream().limit(100).collect(Collectors.toList());
        return ResponseEntity.ok(all);


    }
}
