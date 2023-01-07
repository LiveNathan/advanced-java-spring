package platform.codingnomads.co.corespring.examples.scopeannotaion;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Pie {
    public Pie() {
        System.out.println("We must have a pie. Stress cannot exist in the presence of a pie.");
    }
}
