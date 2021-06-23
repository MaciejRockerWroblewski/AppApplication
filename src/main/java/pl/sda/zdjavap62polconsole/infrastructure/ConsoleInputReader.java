package pl.sda.zdjavap62polconsole.infrastructure;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.util.Scanner;

//Nie wrzucam tutaj stereotypu, zeby pokazac jak to zrobic w @Configuration
@RequiredArgsConstructor
public class ConsoleInputReader {

    private final Scanner scanner;

    public Long readLong() {
        String input = scanner.nextLine();
        return Long.valueOf(input);
    }

    public String readNotEmptyString() {
        String input = scanner.nextLine();
        return Strings.isBlank(input) ? readNotEmptyString() : input;
    }
}
