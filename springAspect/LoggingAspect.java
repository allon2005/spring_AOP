package springAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import springAOP1.Car;

/*
 * Aspect with annotation: Before, after, around, Pointcut, joinpoint, etc
 * Aspect class£º it is a class, which can contain any method;
 * This class exists in a separate package from the package of springAOP1. So, it can be plugged into any other package.
 */

@Aspect   //a modularization of a concern that cuts across multiple classes.  It means this class can be used across multiple classes

public class LoggingAspect {

	////@Before

	/*@Before(value="execution(public void getCarInfo())")  //this set the loggingAdvice() method to run "before the execution of ...." 
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
	//@Before("allDoorMethods()")  // this method will run before any of the method inside the Door.java gets called 
	//Before("allPublicGetters() && allSpringAOP1PackageMethods()")  //combine multiple pointcuts 
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


	 * logginAdvice1 and loggingAdvice1A:   we want both methods are called for any get*() methods. This is the natural way to do it. However, we are repeating @Before
	 * 
	 * We can define @Pointcut (as demonstrated below) for this purpose.  
	 * 
	 * PointCut is the method where you want your service method to attach to (where you wanna cut into the business logic)

	          //@Pointcut

	@Pointcut("execution(public * get*())")
	public void allPublicGetters(){	} //this is a dummy method to hold the @Pointcut value



	@Pointcut("execution(** springAOP2.Door.*(..))")  //a pointcut for all the methods inside the Door.java
	//@Pointcut("within(springAOP2.Door)")  //using "within" (this is more preferable). a pointcut for all the methods inside the Door.java
	public void allDoorMethods(){	} 



	@Pointcut("within(springAOP1.*)")  //all the methods inside the package springAOP1 
	//@Pointcut("within(springAOP1..*)")  //all the methods inside all the subpackages of springAOP1 
	public void allSpringAOP1PackageMethods(){	} 


	@Pointcut("args(Door)")   //all methods taking Door type as argument
	public void argDoorMethod(){}*/



	///////After advice

	@After("execution(public * set*(..))")   //this method will be called after the execution of any "public * set*(..)"
	public void loggingAfterAdvice()
	{
		System.out.println("logging-after advice is running");
	}

	@AfterReturning("execution(public * set*(..))")   //this method will be called after the execution of any "public * set*(..)"
	public void loggingAfterAdvice1()
	{
		System.out.println("logging-after advice1 is running");
	}

	@AfterThrowing("execution(public * set*(..))")   //this method will be called after the  method (public * set*(..)) throws an exception"
	public void loggingAfterExceptionAdvice2()
	{
		System.out.println("logging-after advice2 is running, the method throws an exception");
	}

	//@AfterReturning("execution(springAOP1.Car.setCarInfo3(type))")  //this one does not work to get the argument
	@AfterReturning("args(type)")  // this works to pass the argument to the logging service
	//@AfterReturning("args(name)")  //NOT working. !!!! must use "type",which is the argument name in the method. Othewise, get error message
	//@AfterReturning("args(String)")  //NOT working. !!!! must use "type",which is the argument name in the method. Othewise, get error message
	//public void loggingAdviceArgument(Object type)  //this will work for all the return type
	public void loggingAdviceArgument(String type)
	{
		System.out.println("logging-after advice to get argument is running. The method argumen is : " + type);
	}

	@AfterReturning(pointcut="args(String)",returning="returnString")  // this works to pass the argument to the logging service 
	//@AfterReturning(pointcut="args(type)",returning="returnString")  // Not working. !!! must use the type of the argument used for that method
	public void loggingAdviceReturn(String returnString)  //if the method return an int, should pass "int returnXXX" here
	{
		System.out.println("logging-after advice to get the return value. The method returns : " + returnString);
	}

	@AfterThrowing(pointcut="args(..)",throwing="ex")  // this works to pass the exception threw to the logging service
	public void loggingAdviceCatchException(Exception ex)  
	{
		System.out.println("***logging-after advice to get the exception : " + ex);
	}


	////join point   
	//joinpoint: the place where the pointcut can be applied. It can be method,exception or field.

	@AfterReturning("execution(public * set*(..))")   //this method will be called after the execution of any "public * set*(..)"
	public void loggingAfterAdviceJoinpoint(JoinPoint joinPoint)  //joinPoint holds the information from where the pointcut or AOP is applied. We can extract the info of this joinPoint to do certain actions.
	{
		System.out.println("logging-after advice for join point is running. The joinPoint info is: " + joinPoint.getTarget().getClass());
		Car car = (Car)joinPoint.getTarget(); //we can get the target object and then use it.
		car.getCarInfo();

	}

	///@Around

	@Around("execution(public * set*(..))")
	public void loggingAdviceAround(ProceedingJoinPoint pjp)   //must take at least this argument "ProceedingJoinPoint. It can also take more arguments
	{
		try {

			System.out.println("logging-around(before) advice is running. " );

			pjp.proceed();  //this is the real method to indicate the target method (public * set*(..))invocation. If we can add action before this, the action will be "@Before". If we add action after this, it will be "@after".

			/*//we can also completely bypass this method under certain condition. For example:

			Car car = (Car)pjp.getTarget();
			if("Yellow".equals(car.getColor()))
			{
				pjp.proceed();
			}*/
			
			System.out.println("logging-around(after) advice is running. " );

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}



}

/* Joinpoint vs pointcut
 * see: http://stackoverflow.com/questions/15447397/spring-aop-whats-the-difference-between-joinpoint-and-pointcut
 */

/*Around advice is the most powerful advice type.
 * see: http://www.compiletimeerror.com/2013/05/spring-aop-around-advice-example.html
 * see:http://stackoverflow.com/questions/29193489/how-exactly-does-an-around-advice-work-in-spring-aop
 * 
 */
