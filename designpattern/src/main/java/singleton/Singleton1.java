package singleton;

/**
 * 懒汉，线程不安全
 * 懒汉式单例类.在第一次调用的时候实例化自己
 * @author 张贵东
 * 
 */
public class Singleton1 {
	
	private static Singleton1 instance;

	private Singleton1() {
	}

	public static Singleton1 getInstance() {
		if (instance == null) {
			instance = new Singleton1();
		}
		return instance;
	}

}
