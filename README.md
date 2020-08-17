# ELK ReadeMe

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


