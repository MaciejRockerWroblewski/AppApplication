package pl.sda.zdjavap62polconsole.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.zdjavap62polconsole.service.ApplicationService;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class DeleteAppView {

    private final ApplicationService applicationService;
    private final Scanner scanner;

    public void display() {
        System.out.println("Podaj id aplikacji do usunięcia:");
        Long id = Long.valueOf(scanner.nextLine());

        try {
            applicationService.delete(id);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            System.out.println("BLAD: " + ex.getMessage());
        }
    }
}
