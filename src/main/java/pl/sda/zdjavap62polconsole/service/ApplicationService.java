package pl.sda.zdjavap62polconsole.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.sda.zdjavap62polconsole.infrastructure.Application;
import pl.sda.zdjavap62polconsole.infrastructure.ApplicationRepository;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class ApplicationService {

    private final ApplicationRepository repository;

    public ApplicationService(@Qualifier("memoryRepository") ApplicationRepository repository) {
        this.repository = repository;
    }

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
            repository.save(Application.builder()
                    .name(name)
                    .producer(producer)
                    .version(version)
                    .build());
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
