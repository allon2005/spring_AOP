package springAOP1;

/*
 * spring AOP basic 1
 */


public class Car {
	
	private String carBrand;
	private String color;
	private Door frontDoorL;
	
	
	public Door getFrontDoorL() {
		return frontDoorL;
	}


	public void setFrontDoorL(Door frontDoorL) {
		this.frontDoorL = frontDoorL;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getCarBrand() {
		return carBrand;
	}


	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	
	public void setCarInfo()
	{
		System.out.println("this is a user car info setter without argument");
	}

	public void setCarInfo1() throws Exception
	{
		System.out.println("this is a user car info setter1 withou  argument");
		throw new Exception("test exception"); 
	}
	
	public void setCarInfo2() throws Exception
	{
		System.out.println("this is a user car info setter2 without argument, throw exception");
		throw new Exception("test exception"); 
	}
	
	public void setCarInfo3(String type)
	{
		System.out.println("the type of the car is : " + type); 
	}
	
	public String setCarInfo4(String type)
	{
		return type;
	}
	

	public void getCarInfo()
	{
		System.out.println("the car's brand is " + carBrand );
		System.out.println("the color of the front door is " + getFrontDoorL().getColor() );
	}

}
