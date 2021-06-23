package pl.sda.zdjavap62polconsole.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.zdjavap62polconsole.infrastructure.ConsoleInputReader;

import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MenuView {

    private final List<SubView> subViews;
    private final ConsoleInputReader inputReader;

    public void display() {
        System.out.println("Aplikacje na komputerze");
        while (true) {
            System.out.println("1. Wyswietl wszystkie, 2. Zainstaluj, 3. Aktualizuj, 4. Usun, 5. Wyjdz");
            int option = inputReader.readLong().intValue();

            switch (option) {
                case 1:
                    getByName(ViewName.LIST_APPS).display();
                    break;
                case 2:
                    getByName(ViewName.INSTALL_NEW).display();
                    break;
                case 3:
                    getByName(ViewName.UPDATE_APP).display();
                    break;
                case 4:
                    getByName(ViewName.DELETE_APP).display();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Nieznana opcja menu");
            }
        }
    }

    private SubView getByName(ViewName name) {
        return subViews.stream()
                .filter(view -> view.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nie wspierany widok"));
    }
}
