package factory;
/**
 * 面包
 * @author 张贵东
 *
 */
public class Bread implements Food{

	public Food produce() {
		System.out.println("产生面包。。。");
		return new Bread();
	}
}
