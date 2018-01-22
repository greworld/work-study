package factory.abs;

import factory.Food;

public class Client {
	public static void main(String[] args) {
		DefaultFactory defaultFactory = new DefaultFactory("Can");
		Food food = defaultFactory.getFood();
		System.out.println(food.produce());
	}
}
