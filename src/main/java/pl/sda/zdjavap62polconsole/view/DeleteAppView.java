package pl.sda.zdjavap62polconsole.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.zdjavap62polconsole.infrastructure.ConsoleInputReader;
import pl.sda.zdjavap62polconsole.service.ApplicationService;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class DeleteAppView {

    private final ApplicationService applicationService;
    private final ConsoleInputReader reader;

    public void display() {
        System.out.println("Podaj id aplikacji do usunięcia:");
        Long id = reader.readLong();

        try {
            applicationService.delete(id);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            System.out.println("BLAD: " + ex.getMessage());
        }
    }
}
