package Task02;

import Task02.config.ProjectConfig;
import Task02.domain.Car;
import Task02.domain.Engin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTwo {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Car c = context.getBean(Car.class);
        Engin e = context.getBean(Engin.class);

        System.out.println(c);
        System.out.println(e);
    }
}
