package pl.sda.zdjavap62polconsole.infrastructure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("fileRepository")
//Można albo dodać nawias do stereotypu albo dać adnotacje qualifier
//@Qualifier("fileRepository")
public class FileApplicationRepository implements ApplicationRepository {

    private static final String FILE_PATH = "C:/Users/trutyna/app.txt";

    @Override
    public void save(Application application) {
        List<Application> applications = readFromFile();
        Long currentMaxIndex = applications.stream().mapToLong(Application::getId).max().orElse(0L);
        application.setId(++currentMaxIndex);
        applications.add(application);
        storeInFile(applications);
    }

    @Override
    public void updateVersion(Long id, String newVersion) {
        List<Application> applications = readFromFile();
        applications.stream().filter(app -> app.getId().equals(id))
                .findFirst()
                .ifPresent(app -> app.setVersion(newVersion));

        storeInFile(applications);
    }

    @Override
    public void delete(Long id) {
        List<Application> applications = readFromFile();
        applications.removeIf(app -> app.getId().equals(id));

        storeInFile(applications);
    }

    @Override
    public List<Application> getAll() {
        return readFromFile();
    }

    @Override
    public Optional<Application> getOne(Long id) {
        return Optional.empty();
    }

    private List<Application> readFromFile() {
        Path path = Paths.get(FILE_PATH);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader.lines()
                .map(line -> line.split(";"))
                .map(fields -> Application.builder()
                        .id(Long.valueOf(fields[0]))
                        .producer(fields[1])
                        .name(fields[2])
                        .version(fields[3])
                        .build())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void storeInFile(List<Application> applications) {
        Path path = Paths.get(FILE_PATH);
        try (BufferedWriter writer = Files.newBufferedWriter(path,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Application application: applications) {
                writer.write(String.format("%d;%s;%s;%s",
                    application.getId(), application.getProducer(),
                        application.getName(), application.getVersion()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
