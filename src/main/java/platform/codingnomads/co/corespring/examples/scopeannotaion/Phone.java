package platform.codingnomads.co.corespring.examples.scopeannotaion;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Phone {
    public Phone() {
        System.out.println("The phone is ringing. OMG!");
    }
}
