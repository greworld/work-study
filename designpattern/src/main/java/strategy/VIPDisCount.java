package strategy;
/**
 * VIP打折方案：
 * @author 张贵东
 *
 */
public class VIPDisCount implements DisCount{

	public double calculate(double price) {
		System.out.println("VIP电影票：");
		System.out.println("增加积分！");
		return price*0.5;
	}

}
