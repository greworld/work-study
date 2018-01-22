package factory.method;

import factory.Food;

public class Client {
	public static void main(String[] args) {
		FoodFactory foodFactory = new BreadFactory();
		Food food = foodFactory.getFood();
		System.out.println(food.produce());
	}
}
