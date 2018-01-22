package singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 类似Spring里面的方法，将类名注册，下次从里面直接获取。
 * 
 * @author 张贵东
 * 
 */
public class Singleton7 {
	private static Map<String,Singleton7> map = new HashMap<String,Singleton7>();
	static {
		Singleton7 single = new Singleton7();
		map.put(single.getClass().getName(), single);
	}
	//保护的默认构造子
	protected Singleton7(){}
	//静态工厂方法,返还此类惟一的实例
	public static Singleton7 getInstance(String name) {
		if(name == null) {
			name = Singleton7.class.getName();
		}
		if(map.get(name) == null) {
			try {
				map.put(name, (Singleton7) Class.forName(name).newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return map.get(name);
	}
}
