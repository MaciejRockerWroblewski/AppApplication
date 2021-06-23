package pl.sda.zdjavap62polconsole.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.zdjavap62polconsole.infrastructure.ConsoleInputReader;
import pl.sda.zdjavap62polconsole.service.ApplicationService;

import java.io.Console;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class UpdateAppView implements SubView {

    private final ApplicationService applicationService;
    private final ConsoleInputReader inputReader;

    public void display() {
        System.out.println("Podaj id aplikacji do aktualizacji:");
        Long id = inputReader.readLong();
        System.out.println("Podaj nową wersję: ");
        String newVersion = inputReader.readNotEmptyString();

        try {
            applicationService.update(id, newVersion);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            System.out.println("BLAD: " + ex.getMessage());
        }
    }

    public ViewName getName() {
        return ViewName.UPDATE_APP;
    }
}