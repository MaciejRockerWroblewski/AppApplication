package pl.sda.zdjavap62polconsole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ScannerConfiguration {

    @Bean
    public Scanner consoleInput() {
        return new Scanner(System.in);
    }
}
