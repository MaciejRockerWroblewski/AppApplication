package pl.sda.zdjavap62polconsole.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.zdjavap62polconsole.infrastructure.ConsoleInputReader;
import pl.sda.zdjavap62polconsole.service.ApplicationService;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class InstallNewAppView {

    private final ApplicationService applicationService;
    private final ConsoleInputReader reader;

    public void display() {
        System.out.println("Podaj producenta");
        String producer = reader.readNotEmptyString();
        System.out.println("Podaj nazwę");
        String name = reader.readNotEmptyString();
        System.out.println("Podaj wersję");
        String version = reader.readNotEmptyString();

        try {
            applicationService.installNew(producer, name, version);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            System.out.println("BLAD : " + ex.getMessage());
        }
    }
}
