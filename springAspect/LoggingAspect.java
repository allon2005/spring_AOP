package springAspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/*
 * Aspect with annotation;
 * Aspect class£º it is a class, which can contain any method;
 * This class exists in a separate package from the package of springAOP1. So, it can be plugged into any other package.
 */

@Aspect   //a modularization of a concern that cuts across multiple classes.  It means this class can be used across multiple classes

public class LoggingAspect {

	@Before(value="execution(public void getCarInfo())")  //this set the loggingAdvice() method to run "before the execution of ...." 
	//@Before("execution(public void getCarInfo())")   //this is also fine
	public void loggingAdvice()
	{
		System.out.println("logging advice is running.");
	}
	
	@Before("execution(public * get*())") //using wild card. All getters will be tied to this method.
	                                      //!!!! this will only apply to the real getters. other methods such as "public void getCarInfo()" will NOT be tied to this method!!! 
	public void logginAdvice()
	{
		System.out.println("logging advice is running for every getter.");
	}

}
