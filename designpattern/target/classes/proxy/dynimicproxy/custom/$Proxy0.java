package proxy.dynimicproxy;
import java.lang.reflect.Method;
public class $Proxy0 implements proxy.dynimicproxy.Customer{
GreInvocationHandler h;
public $Proxy0(GreInvocationHandler h) {
this.h = h;
}
public void shopping(){
try{
Method m = proxy.dynimicproxy.Customer.class.getMethod("shopping",new Class[]{});
this.h.invoke(this,m,null);
}catch(Throwable e){e.printStackTrace();}
}
}