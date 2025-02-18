package terrorless.scraping;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ThreatService {

    private final ThreatRepository threatRepository;

    public Mono<List<Threat>> getData() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.terrorless.01ab.net")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri("/trpc/threat.list")
                .retrieve()
                .bodyToMono(ThreatResponse.class)
                .map(ThreatResponse::getThreats)
                .onErrorResume(error -> {
                    System.err.println("Error fetching threats: " + error.getMessage());
                    return Mono.just(List.of());
                });

    }

    public void fetchAndSaveThreats() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.terrorless.01ab.net")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        webClient.get()
                .uri("/trpc/threat.list")
                .retrieve()
                .bodyToMono(ThreatResponse.class)
                .map(ThreatResponse::getThreats)
                .doOnNext(threats -> {
                    for (Threat threat : threats) {
                        if (threat.getTimeline() != null) {
                            for (TimeLine timeLine : threat.getTimeline()) {
                                timeLine.setThreat(threat);
                            }
                        }
                        threatRepository.save(threat);
                    }
                })
                .onErrorResume(error -> {
                    System.err.println("에러: " + error.getMessage());
                    return Mono.just(List.of());
                })
                .subscribe();
    }
}
