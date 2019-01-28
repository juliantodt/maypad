package de.fraunhofer.iosb.maypadbackend.model.serviceaccount;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

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

    /**
     * Constructor for a serviceaccount with a ssh key.
     *
     * @param sshKey ssh key
     */
    public KeyServiceAccount(String sshKey) {
        this.sshKey = sshKey;
    }
}
