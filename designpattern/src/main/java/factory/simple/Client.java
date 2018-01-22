package factory.simple;

import factory.Bread;
import factory.Can;
import factory.Jelly;
/**
 * 简单工厂：顾客必须知道产品的名字
 * @author ThinkPad
 *
 */
public class Client {
	public static void main(String[] args) {
		FoodFactory foodFactory = new FoodFactory();
		//产生面包
		Bread bread = (Bread) foodFactory.getFood("bread");		
		System.out.println(bread.produce());
		//产生罐头
		Can can = (Can) foodFactory.getFood("Can");
		System.out.println(can.produce());
		//产生果冻
		Jelly jelly = (Jelly) foodFactory.getFood("Jelly");
		System.out.println(jelly.produce());
	}
}
