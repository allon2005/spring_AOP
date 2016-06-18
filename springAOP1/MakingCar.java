package springAOP1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MakingCar {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("springAOP1/AOP.xml");  
		Car car = (Car)context.getBean("car");
		car.getCarInfo();  //!!!! if only using loggingAdvice1, this will only invoke the loggingAdvice1 twice. It supposes to invoke it three times (getCarInfo(), getFrontDoorL() and getColor()). Why?????
		/*System.out.println();
		car.getFrontDoorL().getColor();  //!!!! if only using loggingAdvice1, this will invoke the loggingAdvice1 twice, good!
*/
		((ConfigurableApplicationContext)context).close();
	}

}
