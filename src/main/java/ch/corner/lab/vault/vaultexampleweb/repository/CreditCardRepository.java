package ch.corner.lab.vault.vaultexampleweb.repository;

import ch.corner.lab.vault.vaultexampleweb.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
}
