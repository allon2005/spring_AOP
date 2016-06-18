package springAspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/*
 * Aspect with annotation;
 * Aspect class£º it is a class, which can contain any method;
 * This class exists in a separate package from the package of springAOP1. So, it can be plugged into any other package.
 */

@Aspect   //a modularization of a concern that cuts across multiple classes.  It means this class can be used across multiple classes

public class LoggingAspect {

	@Before(value="execution(public void getCarInfo())")  //this set the loggingAdvice() method to run "before the execution of ...." 
	//@Before("execution(public void getCarInfo())")   //this is also fine
	//@Before("execution(public void springAOP1.Car.getCarInfo())")  //this is more explicit to add the package and class for the specific method
	public void loggingAdvice()
	{
		System.out.println("logging advice0 is running.");
	}

	@Before("execution(public * get*())") //using wild card. All get*() methods starts with "public" will be tied to this method.
	//@Before("execution(public * get*(*))") // (*) means can have 1 or more arguments
	//@Before("execution(public * get*(..))") // (..) means can have or can have NO arguments
	//@Before("allPublicGetters()")  // use @Pointcut
	public void loggingAdvice1()
	{
		System.out.println("logging advice1 is running for every getter.");
	}

	@Before("execution(public * get*())")
	//@Before("allPublicGetters()")   // use @Pointcut
	public void loggingAdvice1A()
	{
		System.out.println("logging advice1A is running for every getter.");
	}
	
	/*
	 * logginAdvice1 and loggingAdvice1A:   we want both methods are called for any get*() methods. This is the natural way to do it. However, we are repeating @Before
	 * 
	 * We can define @Pointcut (as demonstrated below) for this purpose.  
	 * 
	 * PointCut is the method where you want your service method to attach to (where you wanna cut into the business logic)
	 */
	
	@Pointcut("execution(public * get*())")
	public void allPublicGetters(){	} //this is a dummy method to hold the @Pointcut value


}
