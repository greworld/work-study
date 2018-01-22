package singleton;

/**
 * 饿汉，变种
 * 
 * @author 张贵东
 * 
 */
public class Singleton4 {
	
	private static Singleton4 instance = null;

	private Singleton4() {
	}
	static{
		instance = new Singleton4();
	}
	public static synchronized Singleton4 getInstance() {
		return instance;
	}

}
