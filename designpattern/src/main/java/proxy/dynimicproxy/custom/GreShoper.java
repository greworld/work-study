package proxy.dynimicproxy.custom;

import proxy.dynimicproxy.Customer;

import java.lang.reflect.Method;

/**
 * @author 风骚的GRE
 * @date 2018/1/22.
 */
public class GreShoper implements GreInvocationHandler {
    private Customer target;
    //获取被代理人的个人资料
    public Object getInstance(Customer target) throws Exception{
        this.target = target;
        Class clazz = target.getClass();
        System.out.println("被代理对象的class是:"+clazz);
        return GrePorxy.newProxyInstance(new GreClassLoader(), clazz.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是商店的销售员，得给你推荐一个商品。。。");
        System.out.println("开始进行挑选...");
        System.out.println("------------");
        method.invoke(this.target, args);
        System.out.println("------------");
        System.out.println("如果合适的话，购买，送货给你");
        return null;
    }
}
