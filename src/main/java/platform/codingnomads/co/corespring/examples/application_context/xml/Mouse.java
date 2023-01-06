package platform.codingnomads.co.corespring.examples.application_context.xml;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Mouse {
    private String brand;
    private String model;
}
