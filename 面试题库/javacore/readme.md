# 1.对于两个Integer的变量a,b，使用swap方法如何去交他们的值？

对于这个问题：我们首先要清除的知道它想要考察什么？首先来看我们自认为正确的做法

~~~java
 public static void main( String[] args ){
        Integer a = 1,b = 2;
        swap(a,b);
        System.out.println("a = "+a+", b = "+b);
    }
    public static void swap(Integer a,Integer b){
        int temp = a;
        a = b;
        b = temp;
    }
~~~

实际运行结果还是a = 1,b = 2;并没有交换过来，这是为什么？

我们查看Integer的源码：

~~~java
public static Integer valueOf(int i) {
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }

    /**
     * The value of the {@code Integer}.
     *
     * @serial
     */
    private final int value;

 static final int low = -128;
        static final int high;
        static final Integer cache[];

        static {
            // high value may be configured by property
            int h = 127;
~~~

知道当Integer的值在-128~127之间时，从缓存中取值，否则，创建一个新的对象。

对于刚刚的交换方法，我们只是操作的值，并没有改变他们引用的地址值，还有一点，value变量时final修饰的

~~~java
 public static void main( String[] args ) throws NoSuchFieldException, IllegalAccessException {
        Integer a = 1,b = 2;
        swap(a,b);
        System.out.println("a = "+a+", b = "+b);
    }
    public static void swap(Integer a,Integer b) throws NoSuchFieldException, IllegalAccessException {
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        Integer temp = new Integer(a);
        field.set(a,b);
        field.set(b,temp);
    }
~~~

总结：这道题主要考察几个点：

反射

Integer的缓存



# 2.hashcode相等的两个类一定相等吗？equals呢？相反呢？



#3、介绍一下集合框架



#4、HashMap、hashtable底层实现有什么区别？Hashtable和ConcurrentHashTable？



#5、HashMap和TreeMap有什么区别？底层实现的数据结构是什么？



#6、线程池用过吗？都有什么参数？底层如何实现？



#7、synchronized和Lock有什么区别，synchronized什么情况下是对象锁，什么时候是全局锁，为什么？



#8、ThreadLocal是什么？底层实现是什么？写一个例子呗？



#9、volatile的工作原理



#10、cas知道吗？如何实现



#11、请至少四种写法完成单例模式？



#12、聊聊ConcurrentHashMap呗？





