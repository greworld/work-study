package proxy.dynimicproxy.jdk;

import proxy.dynimicproxy.Customer;
import proxy.dynimicproxy.Jhon;
import proxy.dynimicproxy.custom.GreShoper;


/**
 * 测试类
 * @author ThinkPad
 *
 */
public class Client {
	public static void main(String[] args) throws Exception{
		 /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数jhon.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
		Customer customer = (Jhon) new GreShoper().getInstance(new Jhon());
		System.out.println(customer.getClass());
		customer.shopping("小米NOT3");
	}
}
