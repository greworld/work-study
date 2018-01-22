package proxy.dynimicproxy;

/**
 * 真实的顾客：需要买一部手机
 * @author ThinkPad
 *
 */
public class Jhon implements Customer {
	private static final String LN = "\r\n"; 
	public void shopping(String name) {
		System.out.println("您好，我需要购买一部手机"+LN);
		System.out.println("我的要求是："+LN);
		System.out.println("手机类型为："+name+LN);
	}

}
