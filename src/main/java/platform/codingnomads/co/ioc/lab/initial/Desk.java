package platform.codingnomads.co.ioc.lab.initial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Desk {
    private String brand;
    private String model;
}
