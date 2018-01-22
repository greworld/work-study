package factory;
/**
 * 罐头
 * @author 张贵东
 *
 */
public class Can implements Food {

	public Food produce() {
		System.out.println("产生罐头。。。");
		return new Can();
	}

}
