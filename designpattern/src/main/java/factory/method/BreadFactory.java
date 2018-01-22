package factory.method;

import factory.Bread;
import factory.Food;

public class BreadFactory implements FoodFactory {

	public Food getFood() {
		return new Bread();
	}

}
