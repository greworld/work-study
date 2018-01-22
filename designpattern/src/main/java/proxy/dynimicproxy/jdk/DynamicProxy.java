package proxy.dynimicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler{
	//代理的真实对象，拿资料
	private Object proxyObject;
	//构造方法，注入代理对象
	public DynamicProxy(Object proxyObject) {
		this.proxyObject = proxyObject;
	}
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		//在代理真实对象前我们可以添加一些自己的操作
        System.out.println("开始代购。。。");    
        System.out.println("Method:" + method);        
        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(proxyObject, args);       
        //在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("代购结束。。。");
		return null;
	}

}
