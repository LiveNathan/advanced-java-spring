package platform.codingnomads.co.corespring.examples.springbeans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@Component
public class SpringDeveloper {

    private Address address;
    private Assets assets;

//    public SpringDeveloper(Address address) {
//        this.address = address;
//    }
}
