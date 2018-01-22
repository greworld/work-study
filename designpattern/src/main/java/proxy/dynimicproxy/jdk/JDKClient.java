package proxy.dynimicproxy.jdk;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

import proxy.dynimicproxy.Jhon;
import proxy.dynimicproxy.Customer;
import sun.misc.ProxyGenerator;

/**
 * 测试类
 * @author ThinkPad
 *
 */
public class JDKClient {
	public static void main(String[] args) {
		//持有真实对象的引用
		Jhon jhon = new Jhon();
		//构造代理对象
		DynamicProxy handler = new DynamicProxy(jhon);
		 /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数jhon.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
		Customer customer = (Customer) Proxy.newProxyInstance(handler.getClass().getClassLoader(), jhon.getClass().getInterfaces(), handler);
		System.out.println(customer.getClass().getName());
		customer.shopping("小米NOT3");
		createProxyClassFile();

	}
	private static void createProxyClassFile() {
		String name = "$Proxy0";
		byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Customer.class});
	    try  
	    {   
	      FileOutputStream out = new FileOutputStream( name + ".class" );   
	      out.write( data );   
	      out.close();   
	    }   
	    catch( Exception e )   
	    {   
	      e.printStackTrace();   
	    }  
	}
}
