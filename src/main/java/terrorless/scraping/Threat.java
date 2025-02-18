package terrorless.scraping;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Threat {
    @Id
    private String id;

    @Column(name = "objectType")
    private String objectType;

    @Column(name = "updatedAt")
    private Long updatedAt;

    @Column(name = "locationName")
    private String locationName;

    @Column(name = "status")
    private String status;

    @Column(name = "createdAt")
    private Long createdAt;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "threat", orphanRemoval = true)
    private List<TimeLine> timeline;
}
