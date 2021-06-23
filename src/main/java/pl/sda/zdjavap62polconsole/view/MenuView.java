package pl.sda.zdjavap62polconsole.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.zdjavap62polconsole.infrastructure.ConsoleInputReader;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MenuView {

    private final ApplicationListView listView;
    private final DeleteAppView deleteAppView;
    private final InstallNewAppView installNewAppView;
    private final UpdateAppView updateAppView;
    private final ConsoleInputReader inputReader;

    public void display() {
        System.out.println("Aplikacje na komputerze");
        while (true) {
            System.out.println("1. Wyswietl wszystkie, 2. Zainstaluj, 3. Aktualizuj, 4. Usun, 5. Wyjdz");
            int option = inputReader.readLong().intValue();

            switch (option) {
                case 1:
                    listView.display();
                    break;
                case 2:
                    installNewAppView.display();
                    break;
                case 3:
                    updateAppView.display();
                    break;
                case 4:
                    deleteAppView.display();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Nie znana opcja menu");
            }
        }
    }
}
