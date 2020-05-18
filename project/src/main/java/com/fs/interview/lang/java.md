# Map相关

## HashMap什么时候扩容
length * factor, factor=0.75，超过这个threshold，进行扩容

## ConcurrentHashMap如何实现线程安全的
jdk7 分段锁，类似于数据库的间隙锁，将数据分成多段，每段都是数组+链表的方式

concurrencyLevel：并发级别，默认16，在一个ConcurrentHashMap创建后Segment的个数是不能变的，扩容过程过改变的是每个Segment的大小。

segment的缺点：
多段，浪费内存空间，段分布不均匀，有些段很大时，锁住的内容很多，性能下降。

jdk1.8

什么时候扩容？
1. 当前容量超过阈值
2. 当链表中元素个数超过默认设定（8个），当数组的大小还未超过64的时候，
此时进行数组的扩容，如果超过则将链表转化成红黑树

为什么不用ReentrantLock而用synchronized ?
减少内存开销:如果使用ReentrantLock则需要节点继承AQS来获得同步支持，
增加内存开销，而1.8中只有头节点需要进行同步。

内部优化:synchronized则是JVM直接支持的，JVM能够在运行时作出相应的优化措施：锁粗化、锁消除、锁自旋等等。
## TreeMap如何实现一致性hash

# finally一定会被调用到吗？
不一定，System.exit(0),当一个线程被中断，服务down机

# 线程相关

## 线程间的通信方式
volatile, sync,

wait,notify

threadlocal

Thread.join

## 线程状态
NEW RUNNABLE BLOCK WAIT TIME_WAIT TERM


# 锁相关
## Volatile 
回写到内存中，读操作性能没有区别，写操作会比较慢，因为
需要在本地代码中插入内存屏障指令保证处理器不发生乱序行为。


## Atomic
Lock-Free算法，乐观锁的实现

##Synchronized效率提升
不断升级，

无(对象hashcode) -> 偏向锁（偏向线程ID) -> 轻量级（指向栈中锁记录的指针）
-> 重量级（指向互斥量）

重量级的锁发生情况就是
1. 线程自旋超过一定次数
2. 一个线程持有锁，一个自旋，又来一个线程
