package pl.sda.zdjavap62polconsole.infrastructure;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository  {

    void save(Application application);

    void updateVersion(Long id, String newVersion);

    void delete(Long id);

    List<Application> getAll();

    Optional<Application> getOne(Long id);
}
