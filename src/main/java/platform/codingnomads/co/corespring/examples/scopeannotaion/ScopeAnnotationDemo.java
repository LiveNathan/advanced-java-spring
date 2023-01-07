package platform.codingnomads.co.corespring.examples.scopeannotaion;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ScopeAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ScopeAnnotationDemoConfig.class);
        ctx.refresh();
        SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);

        System.out.println("-----Hashcode of SingletonBean-----");
        System.out.println(singletonBean1.hashCode());
        System.out.println(singletonBean2.hashCode());

        final PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
        final PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);

        System.out.println("-----Hashcode of PrototypeBean-----");
        System.out.println(prototypeBean1.hashCode());
        System.out.println(prototypeBean2.hashCode());
        System.out.println();

        Phone phone = ctx.getBean(Phone.class);
        Phone phone2 = ctx.getBean(Phone.class);

        System.out.println("-----Hashcode of Phone-----");
        System.out.println(phone.hashCode());
        System.out.println(phone2.hashCode());

        final Pie pie = ctx.getBean(Pie.class);
        final Pie pie2 = ctx.getBean(Pie.class);

        System.out.println("-----Hashcode of Pie-----");
        System.out.println(pie.hashCode());
        System.out.println(pie2.hashCode());
        System.out.println();
        ctx.close();
    }
}
