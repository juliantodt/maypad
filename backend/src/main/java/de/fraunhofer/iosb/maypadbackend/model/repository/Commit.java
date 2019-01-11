package de.fraunhofer.iosb.maypadbackend.model.repository;

import de.fraunhofer.iosb.maypadbackend.model.person.Author;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * A commit in a {@link Repository}.
 *
 * @author Lukas Brosch
 * @version 1.0
 */
@Data
@NoArgsConstructor
@Entity
public class Commit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column
    private String commitMessage;
    @Column
    private String commitIdentifier;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Author author;

    /**
     * Constructor for Commit.
     * @param commitMessage the Commit-Message
     * @param commitIdentifier the Commit-Identifier (hash for git)
     * @param timestamp the exact time of the commit
     * @param author the commit author
     */
    public Commit(String commitMessage, String commitIdentifier, Date timestamp, Author author) {
        this.commitMessage = commitMessage;
        this.commitIdentifier = commitIdentifier;
        this.timestamp = timestamp;
        this.author = author;
    }
}
