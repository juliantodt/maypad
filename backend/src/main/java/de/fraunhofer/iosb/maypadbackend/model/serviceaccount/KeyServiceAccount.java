package de.fraunhofer.iosb.maypadbackend.model.serviceaccount;

import de.fraunhofer.iosb.maypadbackend.services.security.EncryptedText;
import de.fraunhofer.iosb.maypadbackend.services.security.EncryptionService;
import de.fraunhofer.iosb.maypadbackend.util.EncryptionServiceProvider;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Service account whose authentication is using an SSH key.
 *
 * @author Lukas Brosch
 * @version 1.0
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class KeyServiceAccount extends ServiceAccount {

    @Column(name = "ssh_key", columnDefinition = "TEXT")
    private String sshKey;
    @Column
    String salt;

    @Transient
    private EncryptionService encryptionService;

    /**
     * Constructor for a serviceaccount with a ssh key.
     *
     * @param sshKey ssh key
     */
    public KeyServiceAccount(String sshKey) {
        EncryptedText text = encryptionService.encrypt(sshKey);
        this.encryptionService = EncryptionServiceProvider.getEncryptionService();
        this.sshKey = text.getText();
        this.salt = text.getSalt();
    }

    public String getKey() {
        return encryptionService.decrypt(sshKey, salt);
    }
}
