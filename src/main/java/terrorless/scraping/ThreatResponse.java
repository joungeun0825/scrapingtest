package terrorless.scraping;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ThreatResponse {
    private Result result;

    public ThreatResponse() {}

    public List<Threat> getThreats() {
        if (result != null && result.getData() != null && result.getData().getJson() != null) {
            return result.getData().getJson().getThreats();
        }
        return null;
    }
}
