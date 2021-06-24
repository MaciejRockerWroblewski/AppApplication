package pl.sda.zdjavap62polconsole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.sda.zdjavap62polconsole.config.ApplicationConfiguration;
import pl.sda.zdjavap62polconsole.view.MenuView;

@SpringBootApplication
//  W ten sposob mozemy definiowac klasy konfiguracyjne, ale mozemy tez dawac stereotyp nad klasa
// @EnableConfigurationProperties({ApplicationConfiguration.class})
public class Zdjavap62polConsoleApplication implements CommandLineRunner {

    @Autowired
    private MenuView menuView;

    public static void main(String[] args) {
        SpringApplication.run(Zdjavap62polConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        menuView.display();
    }
}
