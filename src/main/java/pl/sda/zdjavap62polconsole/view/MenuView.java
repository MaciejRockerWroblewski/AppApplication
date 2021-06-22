package pl.sda.zdjavap62polconsole.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MenuView {

    private final ApplicationListView listView;
    private final DeleteAppView deleteAppView;
    private final InstallNewAppView installNewAppView;
    private final UpdateAppView updateAppView;

    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Aplikacje na komputerze");
        while (true) {
            System.out.println("1. Wyswietl wszystkie, 2. Zainstaluj, 3. Aktualizuj, 4. Usun, 5. Wyjdz");
            int option = Integer.valueOf(scanner.next());

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
