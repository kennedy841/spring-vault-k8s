package ch.corner.lab.vault.vaultexampleweb.repository;

import ch.corner.lab.vault.vaultexampleweb.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
