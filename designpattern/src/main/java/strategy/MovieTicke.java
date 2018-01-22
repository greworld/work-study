package strategy;

public class MovieTicke {
	private double price;
	
	private DisCount disCount;//维持一个对抽象折扣类的引用  
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public void setDisCount(DisCount disCount){
		this.disCount = disCount;
	}
	
	public double getPrice(){
		return disCount.calculate(price);
	}
}
