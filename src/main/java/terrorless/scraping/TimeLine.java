package terrorless.scraping;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class TimeLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long eventTime;
    private long createdAt;

    @ElementCollection
    private List<String> source;

    private String status;

    @ManyToOne
    @JoinColumn(name = "threat_id", nullable = false)
    private Threat threat;
}
