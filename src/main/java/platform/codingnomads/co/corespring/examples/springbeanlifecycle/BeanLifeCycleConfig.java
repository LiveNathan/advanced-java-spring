package platform.codingnomads.co.corespring.examples.springbeanlifecycle;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class BeanLifeCycleConfig implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("something quirky: ".concat(name));
    }
}
