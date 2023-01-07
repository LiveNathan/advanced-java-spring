package platform.codingnomads.co.corespring.examples.primaryannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PrimaryAnnotationDemo {
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(PrimaryAnnotationDemo.class);
        VideoCard videoCard = ctx.getBean(VideoCard.class);
        String className = videoCard.getClass().getSimpleName();
        System.out.println("\n*** Class name = " + className + " ***\n");

        ctx.close();
    }
}
