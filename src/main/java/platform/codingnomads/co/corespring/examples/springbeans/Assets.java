package platform.codingnomads.co.corespring.examples.springbeans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Assets {
    private String brand;
    private String model;
    private int quantity;
}
