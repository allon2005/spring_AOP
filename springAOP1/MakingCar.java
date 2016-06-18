package springAOP1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MakingCar {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("springAOP1/AOP.xml");  
		Car car = (Car)context.getBean("car");
		car.getCarInfo();

		((ConfigurableApplicationContext)context).close();
	}

}
