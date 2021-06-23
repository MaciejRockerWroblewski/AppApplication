package pl.sda.zdjavap62polconsole.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.zdjavap62polconsole.infrastructure.Application;
import pl.sda.zdjavap62polconsole.service.ApplicationService;

@Component
@RequiredArgsConstructor
public class ApplicationListView implements SubView {

    private final ApplicationService service;

    public void display() {
        service.listAll().forEach(this::displayAppRow);
    }

    public ViewName getName() {
        return ViewName.LIST_APPS;
    }

    private void displayAppRow(Application application) {
        System.out.println(String.format("%d | %20s | %20s | %10s",
                application.getId(), application.getProducer(),
                application.getName(), application.getVersion()));
    }
}
