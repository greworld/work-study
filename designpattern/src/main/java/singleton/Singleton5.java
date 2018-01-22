package singleton;

/**
 * 静态内部类(推荐)
 * 懒汉式（静态内部类）
 * 这种写法，即解决安全问题，又解决了性能问题
 * 这个代码，没有浪费一个字
 * @author 张贵东
 * 
 */
public class Singleton5 {

	//1、先声明一个静态内部类
	//private 私有的保证别人不能修改
	//static 保证全局唯一
	private static class LazyHolder {
		//final 为了防止内部误操作，代理模式，GgLib的代理模式
		private static final Singleton5 INSTANCE = new Singleton5();
	}
	//2、将默认构造方法私有化
	private Singleton5 (){}
	//相当于有一个默认的public的无参的构造方法，就意味着在代码中随时都可以new出来

	//3、同样提供静态方法获取实例
	//final 确保别人不能覆盖
	public static final Singleton5 getInstance() {

		//方法中的逻辑，是要在用户调用的时候才开始执行的
		//方法中实现逻辑需要分配内存，也是调用时才分配的
		return LazyHolder.INSTANCE;
	}

/**
 * 这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，它跟第三种和第四种方式不同的是（很细微的差别）：
 * 第三种和第四种方式是只要Singleton类被装载了，那么instance就会被实例化（没有达到lazy loading效果），而这种方式
 * 是Singleton类被装载了，instance不一定被初始化。因为SingletonHolder类没有被主动使用，只有显示通过调用getInstance方法时，
 * 才会显示装载SingletonHolder类，从而实例化instance。想象一下，如果实例化instance很消耗资源，我想让他延迟加载，
 * 另外一方面，我不希望在Singleton类加载时就实例化，因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，
 * 那么这个时候实例化instance显然是不合适的。这个时候，这种方式相比第三和第四种方式就显得很合理。
 */
}

//我们所写的所有的代码，在java的反射机制面前，都是裸奔的
//反射机制是可以拿到private修饰的内容的
//我们可以理解成即使加上private也不靠谱（按正常套路出牌，貌似可以）

//	static int a = 1;
//	//不管该class有没有实例化，static静态块总会在classLoader执行完以后，就加载完毕
//	static{
//		//静态块中的内容，只能访问静态属性和静态方法
//		//只要是静态方法或者属性，直接可以用Class的名字就能点出来
//		Singleton4.a = 2;
//		//JVM 内存中的静态区，这一块的内容是公共的
//	}

//类装载到JVM中过程
//1、从上往下(必须声明在前，使用在后)
//先属性、后方法
//先静态、后动态