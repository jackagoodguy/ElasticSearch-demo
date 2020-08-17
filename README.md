# ELK ReadeMe

## 快速使用

- 项目中所有ELK描述为：ElasticSearch+LogStash+Kibana
- MySQL数据：使用[ElasticSearch-DataSql文件夹](https://github.com/ShayLau/ElasticSearch-demo/blob/master/ElasticSearch-Demo-DataSql/book.sql)中提供的book.sql文件（数据库自建）
- [ElasticSearch（点击下载）](https://www.elastic.co/downloads/past-releases/elasticsearch-7-2-1)、[LogStash（点击下载）](https://www.elastic.co/downloads/past-releases/logstash-7-2-1)、[Kibana（点击下载）](https://www.elastic.co/downloads/past-releases/kibana-7-2-1)，**项目ELK均使用7.2.1版本**

**

## ELK安装简要说明：

> 安装说明：文中描述为在Windows环境下安装实例，Linux或者MacOS环境请查阅相关资料，或者提Pull Request

### ElasticSearch

**本地集群说明：**
在启动本地ES服务时，想要在一台Windows电脑启动多个ES节点，需要保证集群名称一致，需要特别注意，如果本地不准备了解集群内容或者稍后了解，可先略过该部分配置说明。

**配置文件如下：**
(解压路径下/config/elasticsearch.yml)
注意配置文件使用YAML语法（键值内容格式需要注意空格），可以查看百度说明

```yaml
# ---------------------------------- Cluster -----------------------------------
#
# Use a descriptive name for your cluster:
#
cluster.name: product
#
# ------------------------------------ Node ------------------------------------
#
# Use a descriptive name for the node:
#
node.name: node-1
node.max_local_storage_nodes: 2
#
# Add custom attributes to the node:
#
#node.attr.rack: r1
#

# ---------------------------------- Network -----------------------------------
#
# Set the bind address to a specific IP (IPv4 or IPv6):
#
#network.host: 192.168.0.1
#
# Set a custom port for HTTP:
#
http.port: 9200
#

```

我的配置为**集群名称：project**，**节点名称：node-1，节点端口：9200，**配置的本地最多集群节点为两个。
在bin文件夹下，在Dos窗口使用elasticsearch.bat命令启动节点。命令为：

```yaml
#切换到ElasticSearch\bin文件夹
cd bin
#启动节点
elasticsearch.bat
#等待程序执行后显示本地节点启动成功即可
```

再次启动一个节点，**只需要修改elasticsearch.yml配置文件中的节点名称和节点端口即可**

这样就相当于在project集群启动了两个节点，启动成功如下

![image.png](https://cdn.nlark.com/yuque/0/2020/png/236129/1597659146906-34a2dccb-280d-4e93-adbd-ffc07f7811f5.png#align=left&display=inline&height=493&margin=%5Bobject%20Object%5D&name=image.png&originHeight=493&originWidth=1062&size=72354&status=done&style=none&width=1062)

### LogStash

### Kibana

**

## Es分布式分片介绍

### 为什么索引的主分片数量在创建的时候需要明确？

当索引一个文档的时候，文档会被存储到一个主分片中。 Elasticsearch 如何知道一个文档应该存放到哪个分片中呢？当我们创建文档时，它如何决定这个文档应当被存储在分片 `1` 还是分片 `2` 中呢？
首先这肯定不会是随机的，否则将来要获取文档的时候我们就不知道从何处寻找了。实际上，这个过程是根据下面这个公式决定的：
shard = hash(routing) % number_of_primary_shards
`routing` 是一个可变值，默认是文档的 `_id` ，也可以设置成一个自定义的值。 `routing` 通过 hash 函数生成一个数字，然后这个数字再除以 `number_of_primary_shards` （主分片的数量）后得到 **余数** 。这个分布在 `0` 到 `number_of_primary_shards-1` 之间的余数，就是我们所寻求的文档所在分片的位置。
这就解释了为什么我们要在创建索引的时候就确定好主分片的数量 并且永远不会改变这个数量：因为如果数量变化了，那么所有之前路由的值都会无效，文档也再也找不到了。

## LogStash数据导入篇

[使用LogStash同步MySQL数据库到ElasticSearch](https://www.yuque.com/sourlemon/java/nwdgwe)

## ES数据检索示例

### 索引相关

- 索引Mapping
- 索引创建

### 搜索服务

- 关键字查询（常用字段检索）
- 分类检索
- 与或关键检索
- 多条件检索

## kibana使用

## 参考内容

[ElasticSearch：权威指南(官网)](https://www.elastic.co/guide/cn/elasticsearch/guide/current/routing-value.html)

