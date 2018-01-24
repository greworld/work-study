#基础题

##1、anyway说回GC roots的话题

抽象概念上说，Java/JVM只是要营造出一种“无限内存”的假象，JVM实现只要“尽力而为”就行，撑不住的时候就抛OOME。至于要使用什么GC算法、什么时候触发GC，这些都是实现细节，规范并不管。JVM实现唯一一定需要保证的就是“当前正在使用的对象不能被干掉了”
JDK11准备引入的新的“Epsilon GC”就是一种极端的实现——根本不GC，应用只管new，new到 -Xmx用满了就OOME
线程是程序运行状态的重要组成部分，JVM会管理Java线程。
就是让JVM变得跟PHP一样适合超短运行
连Serial GC都嫌多余
PHP不是号称“就算有内存泄漏也没关系”嘛
超大的内存还是得用类似Azul的Zing或者以后带有ZGC的HotSpot那样才行
Azul Zing里的C4 GC和Oracle新出的给HotSpot的ZGC都是完全并发的
只需要< 10ms的STW，跟堆大小无关。ZGC的算法是抄了Azul的
这个< 10ms的STW也只是实现细节，从算法上是不需要的，实现上也可以努把力消除掉
回到GC roots：怎样叫做“当前正在使用的对象”？其实概念非常简单，不用生记硬背：对于tracing GC来说，当前已经创建的对象中，程序接下来还有可能通过直接或间接引用关系访问到的就是还有潜在的被使用的可能的对象
GC和JIT有分别的、概念不一样的liveness analysis。GC roots是tracing GC标记对象存活的起点（集合）
那么程序里有哪些引用肯定必须保证它引用的对象一定得活着呢？最简单的两个就是：1、引用类型的局部变量；2、当前存活的对象的类，引申开来还有这些类上的引用类型的静态字段
这俩是无论啥JVM、无论啥GC都必须保证要扫描的GC roots
然后JVM还提供JNI接口来让Java与native程序之间互操作，在JNI的一侧可以持有几种级别的对Java对象的引用，例如说JNI local reference、JNI global reference、JNI weak reference等等。这些在支持JNI的JVM上也要有相应的GC的支持，所以JNI references也会作为GC roots的一部分
不是不在safepoint，是不在global safepoint
对每个线程来说，safepoint就是safepoint；但是对整个JVM来说，一个VM operation是要求所有Java线程同时进入safepoint还是只要求单个单个的Java线程进入safepoint是不一样的
在Azul我们把单个线程进入safepoint叫做checkpoint，把所有Java线程都进入safepoint叫做global safepoint
Oracle为了配合新的并发GC们实现了新的“thread-local handshake”，这个跟Azul说的checkpoint是一样的
概念上必须是root的还有其它一些JVM提供出来的服务，例如说String interning隐含着JVM会有一个全局数据结构记录着哪些string被intern了，当然这些string对象也必须存活——嗯但是真的必须存活么。有不少比较忽悠的资料就说到“必须存活”就完事了，却没有把它的条件说清楚
然后讨论下HotSpot VM里的实现
前面说了半天都只是概念上的，然而HotSpot的实现有很多很多很多细节
如果大家感兴趣的话，请在源码里搜索process_roots以及process_strong_roots、gen_process_strong_roots等函数里寻找答案
这些函数的代码结构非常清晰，不需要知道太多基础，光看名字就可以知道了
挑几个例子来说，大家肯定听说过JVM是“可能可以卸载已经加载了的类”的
所谓class unloading
但是如果当前加载的类是root的话，那怎么可能把它们卸载掉？
所以说在支持类卸载的GC里，当前已加载的类并不是strong root
同理，被intern的String，如果除了管理interned String的那个全局数据结构（在HotSpot里叫做StringTable），程序的其它地方都访问不到某个string了，那么这个string也应该可以卸载掉。但是如果StringTable是一个strong root的话怎么可能卸载掉？
所以说在支持收集已经无用的interned string的GC里，StringTable也不是strong root
同理还有好一些东西是可以选择是否要为root的。例如说CodeCache。HotSpot会在运行时生成机器码，例如说解释器是启动的时候生成的、vtable/itable dispatch stub是运行时lazy生成的，然后JIT编译的方法的机器码当然也是运行时生成的
CodeCache里的这些动态生成的代码，特别是JIT编译生成的那些，自身会引用着一些元数据，例如JIT编译的方法的类与方法的元数据，还有可能在代码里内嵌对常量对象的引用，这些都必须当作root来看待
但同时HotSpot也支持CodeCache cleaning（code unloading）
在支持CodeCache cleaning的GC里，CodeCache也不能看作strong root
所以这就很有趣了：什么时候这些数据结构必须看作strong root，什么时候不是？
此外，是完全STW还是部分并发还是完全并发也会影响root的选择与root scanning的实现，今天就先不说了
是否要作为root的最重要的评判标准就是：如果我不人肉强行指定要扫描某个地方，需要存活的对象是否能保证完全扫描到

##2、介绍一下JVM内存模型，用过什么垃圾回收器，说说看？



##3、线上频繁发生Full GC，怎么处理？CPU使用过高怎么办？如何定位问题？如何解决？说一下解决思路和处理办法



##4、知道字节码吗？字节码都有哪些？Integer x = 5 ;int y = 5;x==y都经过哪些步骤？



##5、讲讲类加载机制？都有哪些类加载机器，这些类加载器都加载哪些文件？手写一个Demo



##6、知道osgi吗？它是如何实现的？



##7、请问你做过哪些JVM优化？使用什么方法？达到什么效果？



##8、class.forName("java.lang.String")和String.class.getClassLoader().loadClass("java.lang.String")有什么区别？



