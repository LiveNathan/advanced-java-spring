package platform.codingnomads.co.ioc.examples.constructorinjection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("platform.codingnomads.co.ioc.examples.constructorinjection")
public class LaptopConfiguration {

    @Bean
    public Processor processor() {
        return new Processor(8, "i9");
    }

    @Bean
    public OS os() {
        return new OS("ubuntu");
    }

    @Bean
    public HardDrive hardDrive() {
        return new HardDrive("Semens", "x123", 1000);
    }

    @Bean
    public Monitor monitor() {
        return new Monitor("brand", "model", 11);
    }
}
