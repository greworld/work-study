package factory.abs;

import factory.Food;
import factory.method.JellyFactory;

public abstract class AbstractFactory {
	public abstract Food getFood();
	
	public Food getFood(String name){
		if("bread".equalsIgnoreCase(name)){
			return new BreadFactory().getFood();
	     }else if("can".equalsIgnoreCase(name)){
	          return new CanFactory().getFood();
	     }else if("jelly".equalsIgnoreCase(name)){
	          return new JellyFactory().getFood();
	     }else{
	          return null;
	     }
	}
}
