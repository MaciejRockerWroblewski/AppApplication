package pl.sda.zdjavap62polconsole.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.zdjavap62polconsole.service.ApplicationService;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class UpdateAppView {

    private final ApplicationService applicationService;
    private final Scanner scanner;

    public void display() {
        System.out.println("Podaj id aplikacji do aktualizacji:");
        Long id = Long.valueOf(scanner.nextLine());
        System.out.println("Podaj nową wersję: ");
        String newVersion = scanner.nextLine();

        try {
            applicationService.update(id, newVersion);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            System.out.println("BLAD: " + ex.getMessage());
        }
    }
}