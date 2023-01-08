package platform.codingnomads.co.corespring.lab.nathan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"xml-config/spring_core_lab_configuration.xml"})
public class FearConfig {
    @Bean
    @SuppressWarnings("unused")
    public School school() {
        return new School();
    }
}
