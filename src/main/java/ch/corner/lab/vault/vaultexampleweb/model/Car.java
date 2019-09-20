package ch.corner.lab.vault.vaultexampleweb.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "vehicle")
public class Car {
    @Id
    private int id;
    private String name;
}
