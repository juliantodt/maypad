package de.fraunhofer.iosb.maypadbackend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * A projectgroup in which can have {@link Project}s.
 *
 * @author Lukas Brosch
 * @version 1.0
 */

@Data
@NoArgsConstructor
@Entity
public class Projectgroup {

    @Id
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column
    private String name;
    @Enumerated(EnumType.STRING)
    private Status buildStatus;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    /**
     * Constructor for Projectgorup.
     *
     * @param name        the projectgroup name
     * @param buildStatus the projectgroup BuildStatus
     */
    public Projectgroup(String name, Status buildStatus) {
        this.name = name;
        this.buildStatus = buildStatus;
        projects = new ArrayList<>();
    }

    /**
     * Constructor for Projectgorup.
     *
     * @param name the projectgroup name
     */
    public Projectgroup(String name) {
        this(name, Status.UNKNOWN);
    }

}
