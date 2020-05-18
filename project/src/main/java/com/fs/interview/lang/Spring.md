# Spring 的IOC/AOP
IOC 很简单，就是控制反转，减少对象创建的代价

实现流程：
读取xml/java config的配置 转换为Resource，
然后解析Resource成为BeanDefinition，然后注册到BeanFactory中

AOP 就是解决纵向请求，代码重复性问题

实现就是动态代理

## JDK Proxy与Cglib,AspectJ代理区别
JDK Proxy是针对接口的，Cglib是生成被代理对象的子类来作为代理
两者都是运行时代理，AspectJ是基于字节码级别的，编译期代理，性能更加。

# 循环依赖的问题
bean实例化的步骤：
1. createBeanInstance

2. populateBean填充属性

3. InitializeBean初始化

在2填充属性的过程中，发生循环依赖。

通过3级缓存来解决。

```
/** 一级缓存：用于存放完全初始化好的 bean **/
private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);

/** 二级缓存：存放原始的 bean 对象（尚未填充属性），用于解决循环依赖 */
private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>(16);

/** 三级级缓存：存放 bean 工厂对象，用于解决循环依赖 */
private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>(16);
```

# 动态代理实现方式


# @Transactional,@Async


# BeanFactory和ApplicationContext联系与区别

