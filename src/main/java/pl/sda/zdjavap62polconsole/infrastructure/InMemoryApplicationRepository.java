package pl.sda.zdjavap62polconsole.infrastructure;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("memoryRepository")
public class InMemoryApplicationRepository implements ApplicationRepository {

    //Pole do wyznaczania id, nie ma mozliwosci aby robila to teraz baza danych
    private Long id = 1L;

    private List<Application> applications = new ArrayList<>();

    @Override
    public void save(Application application) {
        application.setId(id++);

        applications.add(application);
    }

    @Override
    public void updateVersion(Long id, String newVersion) {
        applications.stream().filter(app -> app.getId().equals(id))
                .findFirst()
                .ifPresent(app -> app.setVersion(newVersion));
    }

    @Override
    public void delete(Long id) {
        applications.removeIf(app -> app.getId().equals(id));
    }

    @Override
    public List<Application> getAll() {
        return applications;
    }

    @Override
    public Optional<Application> getOne(Long id) {
        return applications.stream()
                .filter(app -> app.getId().equals(id))
                .findFirst();
    }
}
