package factory.abs;

import factory.Food;

public class DefaultFactory extends AbstractFactory{
	private String name;
	private Food food = null; 
	public DefaultFactory(String name) {
		this.name = name;
	}
	public Food getFood() {	
		if (name==null||"".equalsIgnoreCase(name)) {
			food = new BreadFactory().getFood();
		}else{
			food = super.getFood(name);
		}
		return food;
	}
}
