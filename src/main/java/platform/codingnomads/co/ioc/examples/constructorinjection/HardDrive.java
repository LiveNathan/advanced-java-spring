package platform.codingnomads.co.ioc.examples.constructorinjection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HardDrive {
    private String brand;
    private String model;
    private int capacity;
}
