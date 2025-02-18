package terrorless.scraping;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreatRepository extends JpaRepository<Threat, Long> {
}
