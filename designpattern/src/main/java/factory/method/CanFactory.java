package factory.method;

import factory.Can;
import factory.Food;

public class CanFactory implements FoodFactory {

	public Food getFood() {
		return new Can();
	}

}
