# ELK 部署说明

<a name="jkxNv"></a>
## 快速使用
- 项目中所有ELK描述为：ElasticSearch+LogStash+Kibana
- MySQL数据：使用[ElasticSearch-DataSql文件夹](https://github.com/ShayLau/ElasticSearch-demo/blob/master/ElasticSearch-Demo-DataSql/book.sql)中提供的book.sql文件（数据库自建）
- [ElasticSearch（点击下载）](https://www.elastic.co/downloads/past-releases/elasticsearch-7-2-1)、[LogStash（点击下载）](https://www.elastic.co/downloads/past-releases/logstash-7-2-1)、[Kibana（点击下载）](https://www.elastic.co/downloads/past-releases/kibana-7-2-1)，项目ELK均使用7.2.1版本
- 使用说明：本文档描述实际操作在Windows电脑操作，如果使用Linux或者MacOS请查阅相关资料，或者提Pull Request补全文档**
<a name="QWIXq"></a>
## ELK部署使用
[ElasticSearch部署说明]()<br />[LogStash部署说明]()<br />[Kibana部署说明](https://www.yuque.com/sourlemon/java/buwl0q)<br />
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
- [**使用LogStash同步MySQL数据库到ElasticSearch配置说明**](https://www.yuque.com/sourlemon/java/nwdgwe)

**<br />**
<a name="8Zu9p"></a>

## 参考内容
[ElasticSearch：权威指南(官网)](https://www.elastic.co/guide/cn/elasticsearch/guide/current/routing-value.html)

[JDBC导入数据到LogStash官网说明](https://www.elastic.co/guide/en/logstash/current/plugins-inputs-jdbc.html#plugins-inputs-jdbc-tracking_column_type)<br />
<br />[SpringBoot、ES Rest High Level Java API 官网文档](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high.html)<br />

<a name="SIFrl"></a>
## Github源码
[查看源码仓库](https://github.com/ShayLau/ElasticSearch-demo)<br />
<br />_
<a name="8KJ11"></a>

#### _
<a name="SGVWE"></a>
<center>End<center>

<center>公众号：Java编码日志<center>

<center>![qrcode_for_gh_f247e3bfaf7a_258.jpg](https://cdn.nlark.com/yuque/0/2020/jpeg/236129/1591931262870-ff8ec9a2-2bae-4e39-bfb9-3694b07700a2.jpeg#align=left&display=inline&height=158&margin=%5Bobject%20Object%5D&name=qrcode_for_gh_f247e3bfaf7a_258.jpg&originHeight=258&originWidth=258&size=27055&status=done&style=shadow&width=158)<center>


​       

<center>语雀主页：[点击访问](https://www.yuque.com/sourlemon)<br /><center>