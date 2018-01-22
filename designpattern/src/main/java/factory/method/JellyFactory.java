package factory.method;

import factory.Food;
import factory.Jelly;

public class JellyFactory implements FoodFactory {

	public Food getFood() {
		// TODO Auto-generated method stub
		return new Jelly();
	}

}
