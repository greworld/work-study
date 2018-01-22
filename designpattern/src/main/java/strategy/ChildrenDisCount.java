package strategy;
/**
 * 儿童票打折方案
 * @author 张贵东
 *
 */
public class ChildrenDisCount implements DisCount{

	public double calculate(double price) {
		System.out.println("儿童电影票：");
		return price-10;
	}
}
