package factory.simple;

import factory.Bread;
import factory.Can;
import factory.Food;
import factory.Jelly;
/**
 * 食品加工厂：简单工厂，通过传入的食品名称去生产对应的食品
 * @author 张贵东
 *
 */
public class FoodFactory {
	public Food getFood(String name){
	     if("bread".equalsIgnoreCase(name)){
	          return new Bread();
	     }else if("can".equalsIgnoreCase(name)){
	          return new Can();
	     }else if("jelly".equalsIgnoreCase(name)){
	          return new Jelly();
	     }else{
	          return null;
	     }
	}
}
