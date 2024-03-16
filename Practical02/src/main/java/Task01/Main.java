package Task01;

import Task01.config.ProjectConfig;
import Task01.domain.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Car simpleCar = context.getBean("BMW",Car.class);
        System.out.println(simpleCar.getModel()+" "+simpleCar.getMade());

        String s = context.getBean(String.class);
        System.out.println(s);

        Integer n = context.getBean(Integer.class);
        System.out.println(n);
    }
}
