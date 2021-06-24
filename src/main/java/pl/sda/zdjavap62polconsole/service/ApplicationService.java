package pl.sda.zdjavap62polconsole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.zdjavap62polconsole.config.ApplicationConfiguration;
import pl.sda.zdjavap62polconsole.config.ApplicationConfiguration.ProducerConfiguration;
import pl.sda.zdjavap62polconsole.infrastructure.Application;
import pl.sda.zdjavap62polconsole.infrastructure.ApplicationRepository;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationConfiguration configuration;

    public void installNew(String producer, String name, String version) {
        if (isBlank(producer) || isBlank(name) || isBlank(version)) {
            throw new IllegalStateException("All data is required");
        }

        Optional<Long> maybeAppId = listAll().stream()
                .filter(app -> app.getName().equals(name) && app.getProducer().equals(producer))
                .findFirst()
                .map(Application::getId);

        if (maybeAppId.isPresent()) {
            update(maybeAppId.get(), version);
        } else {
            validateNewApplication(producer, name);

            repository.save(Application.builder()
                    .name(name)
                    .producer(producer)
                    .version(version)
                    .build());
        }
    }

    private void validateNewApplication(String producer, String application) {
        List<Application> allApps = repository.getAll();

        if (allApps.size() >= configuration.getMaxCount()) {
            throw new IllegalStateException("Osiągnięto maksymalną liczbę aplikacji");
        }
        if (configuration.getBannedProducers().contains(producer)) {
            throw new IllegalStateException("Nie możesz instalowac aplikacji tego producenta");
        }
        if (configuration.getProducerConfigurations().containsKey(producer.toLowerCase())) {
            long appCount = allApps.stream().filter(app -> app.getProducer().equalsIgnoreCase(producer)).count();
            ProducerConfiguration producerConfiguration =
                    configuration.getProducerConfigurations().get(producer.toLowerCase());
            if (appCount >= producerConfiguration.getMaxCountPerProducer()) {
                throw new IllegalStateException("Osiągnięto maksymalną liczbę aplikacji dla producenta");
            }
            if (!producerConfiguration.getAllowedApps().contains(application)) {
                throw new IllegalStateException("Nie możesz instalowac tej aplikacji");
            }
        }
    }

    public void update(Long id, String version) {
        if (!repository.getOne(id).isPresent()) {
            throw new IllegalStateException("Given app not exists");
        }

        repository.updateVersion(id, version);
    }

    public void delete(Long id) {
        if (!repository.getOne(id).isPresent()) {
            throw new IllegalStateException("Given app not exists");
        }

        repository.delete(id);
    }

    public List<Application> listAll() {
        return repository.getAll();
    }

}
