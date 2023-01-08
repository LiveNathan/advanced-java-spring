package platform.codingnomads.co.corespring.examples.importannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Love {
    @Bean
    @Primary
    public SpringDeveloper springDeveloper2() {
        return new SpringDeveloper();
    }
}
