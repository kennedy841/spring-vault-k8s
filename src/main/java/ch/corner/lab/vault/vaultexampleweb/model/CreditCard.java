package ch.corner.lab.vault.vaultexampleweb.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "creditcard")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Convert(converter = VaultSecretConverter.class)
    private String pan;


}
