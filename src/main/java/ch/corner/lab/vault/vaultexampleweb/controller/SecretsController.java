package ch.corner.lab.vault.vaultexampleweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SecretsController {

    @Autowired
    Environment env;

    @GetMapping("/secrets/{key}")
    public ResponseEntity<?> get(@PathVariable String key){
        String api_key = env.getProperty(key);
        return ResponseEntity.ok(api_key);


    }
}
