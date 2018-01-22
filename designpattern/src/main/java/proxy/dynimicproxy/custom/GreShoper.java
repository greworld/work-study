package proxy.dynimicproxy.custom;

import proxy.dynimicproxy.Customer;

import java.lang.reflect.Method;

/**
 * @author ��ɧ��GRE
 * @date 2018/1/22.
 */
public class GreShoper implements GreInvocationHandler {
    private Customer target;
    //��ȡ�������˵ĸ�������
    public Object getInstance(Customer target) throws Exception{
        this.target = target;
        Class clazz = target.getClass();
        System.out.println("����������class��:"+clazz);
        return GrePorxy.newProxyInstance(new GreClassLoader(), clazz.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("�����̵������Ա���ø����Ƽ�һ����Ʒ������");
        System.out.println("��ʼ������ѡ...");
        System.out.println("------------");
        method.invoke(this.target, args);
        System.out.println("------------");
        System.out.println("������ʵĻ��������ͻ�����");
        return null;
    }
}
