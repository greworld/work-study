package proxy.dynimicproxy.custom;

import java.lang.reflect.Method;

/**
 * @author ·çÉ§µÄGRE
 * @date 2018/1/22.
 */
public interface GreInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
