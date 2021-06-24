package pl.sda.zdjavap62polconsole.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "apps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationConfiguration {

    private Integer maxCount;
    private List<String> bannedProducers;
    private Map<String, ProducerConfiguration> producerConfigurations;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class ProducerConfiguration {
        private Integer maxCountPerProducer;
        private List<String> allowedApps;
    }
}
