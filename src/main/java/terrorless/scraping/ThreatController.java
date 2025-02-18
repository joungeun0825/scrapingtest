package terrorless.scraping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/threats")
public class ThreatController {
    private final ThreatService threatService;

    public ThreatController(ThreatService threatService) {
        this.threatService = threatService;
    }

    @GetMapping("/saveThreats")
    public void fetchAndSaveThreats() {
        threatService.fetchAndSaveThreats();
    }

    @GetMapping("/getThreats")
    public Mono<List<Threat>> getThreats() {
        return threatService.getData();
    }
}
