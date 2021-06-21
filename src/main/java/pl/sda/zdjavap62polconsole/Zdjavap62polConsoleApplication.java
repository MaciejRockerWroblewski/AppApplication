package pl.sda.zdjavap62polconsole;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Zdjavap62polConsoleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Zdjavap62polConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello world !!");

    }
}
