package pl.sda.zdjavap62polconsole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sda.zdjavap62polconsole.infrastructure.ConsoleInputReader;

import java.util.Scanner;

@Configuration
public class ConsoleInputConfiguration {

    @Bean
    public ConsoleInputReader consoleInputReader(Scanner scanner) {
        return new ConsoleInputReader(scanner);
    }
}
