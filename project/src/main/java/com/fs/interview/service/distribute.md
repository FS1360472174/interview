共识算法
常用于leader选举，日志复制等

# basic paxos
多节点对某个值达成共识

实现过程：
Prepare阶段
只发送propose num,不发送值

Accept阶段
发送proprose num，和值


关键点有两处：
1. 已经accept的值是没法修改了
2. proposer accept阶段和propose阶段的propose_num值和value不一样的，根据prepare
阶段的返回结果来调整的


# multi-paxos
主要是通过leader来省略掉prepare阶段，


与一致性的区别

一致性是对某个值已经是确定的了，然后从各个节点中获取值是相同的。
比如主从一致性。


# paxos和raft
Raft 算法属于 Multi-Paxos 算法
（比如 Etcd、Consul、CockroachDB）。

Raft是通过以leader为准，实现一系列值的共识和各节点日志的一致。

# zab协议
Zookeeper Atomic Broadcast,Zookeeper 原子广播协议。master-slave集群架构模式下，保证各个节点之间的数据一致性
在multi-paxos基础上，实现了数据的最终一致性。因为multi-paxos并不保证数据的顺序，只保证提交达成共识。

## 如何生成分布式id

## 如何生成全局唯一id
订单号，优惠券码，可读性

## 如何生成全局唯一，递增的id

使用号段模式，可以避免多次请求数据库。
但是sequence表不要是主从，否则同步的时候可能有问题。

# 参考
https://juejin.im/post/5cb00706e51d456e3428c0c9

https://web.stanford.edu/~ouster/cgi-bin/papers/OngaroPhD.pdf
