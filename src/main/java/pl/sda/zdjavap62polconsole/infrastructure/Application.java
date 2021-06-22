package pl.sda.zdjavap62polconsole.infrastructure;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Application {
    private Long id;
    private String name;
    private String producer;
    private String version;

}
