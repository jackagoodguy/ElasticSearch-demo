# ELK ReadeMe

## 快速使用
- 项目中所有ELK描述为：ElasticSearch+LogStash+Kibana
- MySQL数据：使用[ElasticSearch-DataSql文件夹](https://github.com/ShayLau/ElasticSearch-demo/blob/master/ElasticSearch-Demo-DataSql/book.sql)中提供的book.sql文件（数据库自建）
- [ElasticSearch（点击下载）](https://www.elastic.co/downloads/past-releases/elasticsearch-7-2-1)、[LogStash（点击下载）](https://www.elastic.co/downloads/past-releases/logstash-7-2-1)、[Kibana（点击下载）](https://www.elastic.co/downloads/past-releases/kibana-7-2-1)，项目ELK均使用7.2.1版本
- 使用说明：本文档描述实际操作在Windows电脑操作，如果使用Linux或者MacOS请查阅相关资料，或者提Pull Request补全文档
## ELK部署简要说明：

### ElasticSearch
#### 本地集群说明
想要在一台Windows电脑部署多个ES节点，需要保证集群名称一致，需要特别注意，如果只是部署单个Es或稍后了解，可先略过多节点部分配置说明**。**
**单节点配置文件：**
配置文件位置：XX解压路径/config/elasticsearch.yml
注意配置文件使用YAML语法（键值对格式,需要注意空格），具体可以查看百度YAML语法说明
我的配置内容大概为：集群名称：project，节点名称：node-1，节点端口：9200，一台本地电脑最多存储的鸡蛋数量为两个（配置一机两节点，集群使用）。
```yaml
# ---------------------------------- Cluster -----------------------------------
# 使用一个名称描述你的集群
# Use a descriptive name for your cluster:
#
cluster.name: product
#
# ------------------------------------ Node ------------------------------------
#
# Use a descriptive name for the node:
# 使用一个名称描述你的节点
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
**启动ES节点说明：**
在ES解压路径中的bin文件夹中有elasticsearch.bat批处理命令，在DOS窗口使用elasticsearch.bat命令启动节点。
命令如下：
```yaml
#切换到ElasticSearch\bin文件夹
cd bin
#启动节点
elasticsearch.bat
#等待程序执行后显示本地节点启动成功即可
```


**本地集群多节点说明：**
再次启动一个节点，**只需要修改elasticsearch.yml配置文件中的节点名称和节点端口即可，集群名称不需修改**
我在本地Windows环境中使用集群product分别启动了节点node-1(端口9200)和节点node-2(端口9201)，使用插件ElasticSearch-head查看如下所示：
注意：如果本地未使用ElasticSearch-head插件，可以查看命令行窗口节点是否启动成功即可
ElasticSearch-head截图如下：
![image.png](https://cdn.nlark.com/yuque/0/2020/png/236129/1597659146906-34a2dccb-280d-4e93-adbd-ffc07f7811f5.png#align=left&display=inline&height=348&margin=%5Bobject%20Object%5D&name=image.png&originHeight=493&originWidth=1062&size=72354&status=done&style=none&width=749)
### LogStash
> Logstash is an open source data collection engine with real-time pipelining capabilities. Logstash can dynamically unify data from disparate sources and normalize the data into destinations of your choice. Cleanse and democratize all your data for diverse advanced downstream analytics and visualization use cases.
> While Logstash originally drove innovation in log collection, its capabilities extend well beyond that use case. Any type of event can be enriched and transformed with a broad array of input, filter, and output plugins, with many native codecs further simplifying the ingestion process. Logstash accelerates your insights by harnessing a greater volume and variety of data.

LogStash是一个开源的数据收集引擎，具有实时管道功能，可以动态统一来自异类数据源的数据和正常数据到你选择的目的地。
















### Kibana
> **官网说明：**
> **_Explore and visualize your data and manage all things Elastic Stack._**
> Whether you’re a user or admin, Kibana makes your data actionable by providing three key functions. Kibana is:
> - **An open-source analytics and visualization platform.** Use Kibana to explore your Elasticsearch data, and then build beautiful visualizations and dashboards.
> - **A UI for managing the Elastic Stack.** Manage your security settings, assign user roles, take snapshots, roll up your data, and more — all from the convenience of a Kibana UI.
> - **A centralized hub for Elastic’s solutions.** From log analytics to document discovery to SIEM, Kibana is the portal for accessing these and other capabilities.



探索形象化你的数据和管理所有的Elastic栈的内容，通过Kibana，我们可以在Web查看Elastic的相关内容，本项目中可以通过Kibana处理ElasticSearch相关内容


#### ElasticSearch集群节点信息查看


**1.配置Kibana需要监控的ElasticSearch节点**
在上面的ES部分，部署了两个节点，使用Kibana查看ES节点信息，只需要在Kibana中配置ElasticSearch的节点信息即可，
配置文件示例如下（**kibana解压路径config文件中kibana.yml**）：
指定elasticsearch.hosts内容，为你本地部署的即可，我的配置Es节点信息为 [**"http://localhost:9200","http://localhost:9201"]**
```yaml
# Kibana is served by a back end server. This setting specifies the port to use.
#server.port: 5601

# Specifies the address to which the Kibana server will bind. IP addresses and host names are both valid values.
# The default is 'localhost', which usually means remote machines will not be able to connect.
# To allow connections from remote users, set this parameter to a non-loopback address.
#server.host: "localhost"

# The Kibana server's name.  This is used for display purposes.
#server.name: "your-hostname"

# The URLs of the Elasticsearch instances to use for all your queries.
elasticsearch.hosts: ["http://localhost:9200","http://localhost:9201"]

# If your Elasticsearch is protected with basic authentication, these settings provide
# the username and password that the Kibana server uses to perform maintenance on the Kibana
# index at startup. Your Kibana users still need to authenticate with Elasticsearch, which
# is proxied through the Kibana server.
elasticsearch.username: "elastic"
elasticsearch.password: "yDaYCXL6KYgNligMpSwd"
```
**2.启动kibana**
和ES一样，通过执行bin文件夹路径下的kibana.bat批处理命令，即可启动，配置文件中指定的服务器端点为5601
```yaml
#切换到kibana\bin文件夹
cd d:\kinaba-7.2.1\bin
#启动kibana
kibana.bat
#等待批处理执行显示start成功即可
```
**3.访问kibana可视化监控页面**
打开浏览器，访问localhost:5601(根据具体配置而定打开不同路径)，界面如下：
![image.png](https://cdn.nlark.com/yuque/0/2020/png/236129/1597718522799-2a17673c-ba26-48e7-99c9-720e4ada0d2e.png#align=left&display=inline&height=346&margin=%5Bobject%20Object%5D&name=image.png&originHeight=902&originWidth=1841&size=167624&status=done&style=none&width=707)


#### 配置监控索引说明
在面板中按照步骤，设置一个索引模板规则，已方便后面在kibana-discover中访问索引信息
**Step 1（创建索引模板）：**
**![image.png](https://cdn.nlark.com/yuque/0/2020/png/236129/1597718885867-20ba4694-e658-4854-bca1-20bf3211278e.png#align=left&display=inline&height=372&margin=%5Bobject%20Object%5D&name=image.png&originHeight=715&originWidth=1748&size=117782&status=done&style=none&width=909)**
**Step 2（创建索引模板）：**
![image.png](https://cdn.nlark.com/yuque/0/2020/png/236129/1597718970489-79081845-b91a-4c1a-bc37-ddf0b8ba102d.png#align=left&display=inline&height=355&margin=%5Bobject%20Object%5D&name=image.png&originHeight=483&originWidth=1233&size=54254&status=done&style=none&width=906)
**Step 3（设置索引时间过滤字段）：**
![image.png](https://cdn.nlark.com/yuque/0/2020/png/236129/1597719136258-8ce197c5-7910-44ed-8398-d792604d2cdc.png#align=left&display=inline&height=292&margin=%5Bobject%20Object%5D&name=image.png&originHeight=528&originWidth=1649&size=101225&status=done&style=none&width=913)
**Step 4（访问kibana-discover）：**
**![image.png](https://cdn.nlark.com/yuque/0/2020/png/236129/1597719592389-aa362d04-4352-4af3-9539-abd6a2aa25e8.png#align=left&display=inline&height=433&margin=%5Bobject%20Object%5D&name=image.png&originHeight=843&originWidth=1917&size=317172&status=done&style=none&width=984)**
#### Kibana面板说明
![image.png](https://cdn.nlark.com/yuque/0/2020/png/236129/1597720122606-f8f6e3dd-f5b5-4873-99f0-484788e0ce8d.png#align=left&display=inline&height=381&margin=%5Bobject%20Object%5D&name=image.png&originHeight=882&originWidth=1903&size=129677&status=done&style=none&width=822)

## 项目代码使用说明
**环境：JDK8、SpringBoot、**


### 项目代码主要内容
#### 索引相关

   - 索引Mapping
   - 索引创建
#### 搜索服务

   - 关键字查询（常用字段检索）
   - 分类检索
   - 与或关键检索
   - 多条件检索


## 重要相关知识点

- [**为什么索引的主分片数量在创建的时候需要明确？**](https://www.elastic.co/guide/cn/elasticsearch/guide/current/routing-value.html)
- [**使用LogStash同步MySQL数据库到ElasticSearch配置说明**](https://www.yuque.com/sourlemon/java/nwdgwe)


## 参考内容
[ElasticSearch：权威指南(官网)](https://www.elastic.co/guide/cn/elasticsearch/guide/current/routing-value.html)

[JDBC导入数据到LogStash官网说明](https://www.elastic.co/guide/en/logstash/current/plugins-inputs-jdbc.html#plugins-inputs-jdbc-tracking_column_type)


[SpringBoot、ES Rest High Level Java API 官网文档](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high.html)


_
#### _
#### _-End-_
_
公众号：Java编码日志
![qrcode_for_gh_f247e3bfaf7a_258.jpg](https://cdn.nlark.com/yuque/0/2020/jpeg/236129/1591931262870-ff8ec9a2-2bae-4e39-bfb9-3694b07700a2.jpeg#align=left&display=inline&height=158&margin=%5Bobject%20Object%5D&name=qrcode_for_gh_f247e3bfaf7a_258.jpg&originHeight=258&originWidth=258&size=27055&status=done&style=shadow&width=158)
语雀主页：[点击访问](https://www.yuque.com/sourlemon)


