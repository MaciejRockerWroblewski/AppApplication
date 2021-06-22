package pl.sda.zdjavap62polconsole.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.zdjavap62polconsole.service.ApplicationService;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class InstallNewAppView {

    private final ApplicationService applicationService;

    public void display() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj producenta");
        String producer = scanner.nextLine();
        System.out.println("Podaj nazwę");
        String name = scanner.nextLine();
        System.out.println("Podaj wersję");
        String version = scanner.nextLine();

        try {
            applicationService.installNew(producer, name, version);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            System.out.println("BLAD : " + ex.getMessage());
        }
    }
}
