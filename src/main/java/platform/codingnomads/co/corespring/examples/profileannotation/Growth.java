package platform.codingnomads.co.corespring.examples.profileannotation;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("deploy")
public class Growth {
    public Growth() {
        System.out.println("So many growth tactics.");
    }
}
