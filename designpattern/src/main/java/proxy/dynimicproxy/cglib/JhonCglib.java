package proxy.dynimicproxy.cglib;

import java.lang.reflect.Method;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


public class JhonCglib implements MethodInterceptor{
	private Object target;
	/**
     * 创建代理实例
     * @param target
     * @return
     */

    public Object getInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();

    }

    /**
     * 实现MethodInterceptor接口要重写的方法。
     * 回调方法
     */
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("任务开始。。。");    
        Object result = proxy.invokeSuper(obj, args);    
        System.out.println("任务结束。。。");    
        return result;    
    }


}
