package strategy;
/**
 * 电影票打折规范接口：
 * @author 张贵东
 *
 */
public interface DisCount {
	//计算打折票价
	public double calculate(double price);
}
