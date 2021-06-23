package pl.sda.zdjavap62polconsole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sda.zdjavap62polconsole.infrastructure.ConsoleInputReader;

import java.util.Scanner;

@Configuration
public class ConsoleInputConfiguration {

    @Bean
    public Scanner consoleInput() {
        return new Scanner(System.in);
    }

    @Bean
    public ConsoleInputReader consoleInputReader() {
        return new ConsoleInputReader(consoleInput());
    }
}
