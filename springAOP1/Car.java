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


	public void getCarInfo()
	{
		System.out.println("the car's brand is " + carBrand );
		System.out.println("the color of the front door is " + this.getFrontDoorL().getColor() );
	}

}
