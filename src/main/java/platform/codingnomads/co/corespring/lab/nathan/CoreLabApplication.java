package platform.codingnomads.co.corespring.lab.nathan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class CoreLabApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(FearConfig.class);
        ctx.refresh();
        @SuppressWarnings("unused")
        final Tongue tongue = ctx.getBean(Tongue.class);
        @SuppressWarnings("unused")
        final School school = ctx.getBean(School.class);
        @SuppressWarnings("unused")
        final Poll poll = ctx.getBean(Poll.class);
        ctx.close();
    }
}
