---
typora-copy-images-to: img
---

# ELK 部署说明

<a name="jkxNv"></a>
## 快速使用
- 项目中所有ELK描述为：ElasticSearch+LogStash+Kibana
- MySQL数据：使用[ElasticSearch-DataSql文件夹](https://github.com/ShayLau/ElasticSearch-demo/blob/master/ElasticSearch-Demo-DataSql/book.sql)中提供的book.sql文件（数据库自建）
- [ElasticSearch（点击下载）](https://www.elastic.co/downloads/past-releases/elasticsearch-7-2-1)、[LogStash（点击下载）](https://www.elastic.co/downloads/past-releases/logstash-7-2-1)、[Kibana（点击下载）](https://www.elastic.co/downloads/past-releases/kibana-7-2-1)，项目ELK均使用7.2.1版本
- 使用说明：本文档描述实际操作在Windows电脑操作，如果使用Linux或者MacOS请查阅相关资料，或者提Pull Request补全文档
<a name="QWIXq"></a>
## ELK部署使用
[ElasticSearch部署说明](https://www.yuque.com/sourlemon/java/cfogy2)<br />[LogStash部署说明](https://www.yuque.com/sourlemon/java/nk77gg)<br />[Kibana部署说明](https://www.yuque.com/sourlemon/java/buwl0q)<br />
<a name="ads2J"></a>

## 项目代码使用说明
**环境：JDK8、SpringBoot、**<br />

<a name="GKWXU"></a>
### 项目代码主要内容
<a name="MyR4J"></a>
#### 索引相关

   - 索引Mapping
   - 索引创建
<a name="cyYic"></a>
#### 搜索服务

   - 关键字查询（常用字段检索）
   - 分类检索
   - 与或关键检索
   - 多条件检索
     <a name="z1XTn"></a>

## 重要相关知识点

- [**为什么索引的主分片数量在创建的时候需要明确？**](https://www.elastic.co/guide/cn/elasticsearch/guide/current/routing-value.html)

**<br />**
<a name="8Zu9p"></a>

## 参考内容
[ElasticSearch：权威指南(官网)](https://www.elastic.co/guide/cn/elasticsearch/guide/current/routing-value.html)

[JDBC导入数据到LogStash官网说明](https://www.elastic.co/guide/en/logstash/current/plugins-inputs-jdbc.html#plugins-inputs-jdbc-tracking_column_type)<br />
<br />[SpringBoot、ES Rest High Level Java API 官网文档](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high.html)<br />

<a name="SIFrl"></a>
## Github源码
[查看源码仓库](https://github.com/ShayLau/ElasticSearch-demo)<br />



## 我的

[语雀主页点击访问](https://www.yuque.com/sourlemon)

### 项目交流

[qq群](https://github.com/ShayLau/ElasticSearch-demo/blob/master/img/Java%E7%BC%96%E7%A0%81%E6%97%A5%E5%BF%97%E7%BE%A4%E4%BA%8C%E7%BB%B4%E7%A0%81.png)