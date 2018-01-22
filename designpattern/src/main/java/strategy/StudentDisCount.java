package strategy;
/**
 * 学生票打折方案：
 * @author 张贵东
 *
 */
public class StudentDisCount implements DisCount{

	public double calculate(double price) {
		System.out.println("学生电影票：");
		return price*0.8;
	}

}
