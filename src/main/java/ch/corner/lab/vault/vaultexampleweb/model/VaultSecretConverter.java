package ch.corner.lab.vault.vaultexampleweb.model;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Ciphertext;
import org.springframework.vault.support.Plaintext;

import javax.persistence.AttributeConverter;

public class VaultSecretConverter implements AttributeConverter<String, String>, ApplicationContextAware {


    private static ApplicationContext appCtx;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        VaultOperations vaultOps = vaultOperations();

        Plaintext plaintext = Plaintext.of(attribute);
        String cipherText = vaultOps.opsForTransit().encrypt("pan", plaintext).getCiphertext();

        return cipherText;
    }

    private VaultOperations vaultOperations() {
        return appCtx.getBean(VaultOperations.class);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        VaultOperations vaultOps = vaultOperations();
        Ciphertext ciphertext = Ciphertext.of(dbData);
        String plaintext = vaultOps.opsForTransit().decrypt("pan", ciphertext).asString();
        return plaintext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtx = applicationContext;
    }
}
