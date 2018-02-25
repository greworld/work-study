# 基础

## 1、Spring都有哪些机制？AOP底层如何实现？IOC呢？



## 2、CGLIB知道吗？它和JDK动态代理有何区别？手动写一个JDK动态代理？

## 3、什么是Spring 框架？Spring 框架有哪些主要模块？

Spring 框架是一个为Java 应用程序的开发提供了综合、广泛的基础性支持的Java 平台。Spring
帮助开发者解决了开发中基础性的问题，使得开发人员可以专注于应用程序的开发。Spring 框
架本身亦是按照设计模式精心打造，这使得我们可以在开发环境中安心的集成Spring 框架，不
必担心Spring 是如何在后台进行工作的。
Spring 框架至今已集成了20 多个模块。这些模块主要被分如下图所示的核心容器、数据访问/
集成,、Web、AOP（面向切面编程）、工具、消息和测试模块。

![spring-framework](C:\Users\Administrator\Desktop\images\spring-framework.png)

## 4、使用Spring 框架能带来哪些好处？

下面列举了一些使用Spring 框架带来的主要好处：
1、Dependency Injection(DI) 方法使得构造器和JavaBean properties 文件中的依赖关系一目了然。

2、与EJB 容器相比较，IOC 容器更加趋向于轻量级。这样一来IOC 容器在有限的内存和CPU资源的情况下进行应用程序的开发和发布就变得十分有利。

3、Spring 并没有闭门造车，Spring 利用了已有的技术比如ORM 框架、logging 框架、J2EE、Quartz 和JDK Timer，以及其他视图技术。

4、Spring 框架是按照模块的形式来组织的。由包和类的编号就可以看出其所属的模块，开发者仅仅需要选用他们需要的模块即可。

5、要测试一项用Spring 开发的应用程序十分简单，因为测试相关的环境代码都已经囊括在框架中了。更加简单的是，利用JavaBean 形式的POJO 类，可以很方便的利用依赖注入来写入测试数据。

5、Spring 的Web 框架亦是一个精心设计的Web MVC 框架，为开发者们在web 框架的选择上提供了一个除了主流框架比如Struts、过度设计的、不流行web 框架的以外的有力选项。

6、Spring 提供了一个便捷的事务管理接口，适用于小型的本地事务处理（比如在单DB 的环境下）和复杂的共同事务处理（比如利用JTA 的复杂DB 环境）。



