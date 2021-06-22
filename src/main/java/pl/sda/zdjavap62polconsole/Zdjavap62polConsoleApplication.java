package pl.sda.zdjavap62polconsole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.zdjavap62polconsole.view.MenuView;

@SpringBootApplication
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
