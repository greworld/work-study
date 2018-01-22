package strategy;
/**
 * 测试类：
 * @author 张贵东
 *
 */
public class Client {
	public static void main(String[] args) {
		MovieTicke movieTicke = new MovieTicke();
		double originalPrice = 60.0;//原始票价
		double currentPrice;
		
		movieTicke.setPrice(originalPrice);
		System.out.println("原始票价为："+originalPrice);
		System.out.println("-----------------------");
		
		DisCount disCount; 
		disCount = (DisCount)XMLUtil.getBean(); //读取配置文件并反射生成具体折扣对象
		//注入打折对象
		movieTicke.setDisCount(disCount);
		
		currentPrice = movieTicke.getPrice();
		
		System.out.println("打折后的票价为："+currentPrice);
	}
}
