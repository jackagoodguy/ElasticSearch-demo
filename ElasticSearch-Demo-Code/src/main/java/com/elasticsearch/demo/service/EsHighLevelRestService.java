package com.elasticsearch.demo.service;

import com.elasticsearch.demo.listener.GetResponseListener;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: ShayLau
 * @Date: 2020/8/17 10:31
 */
@Service
@Slf4j
public class EsHighLevelRestService {

    @Autowired
    private RestHighLevelClient highLevelClient;


    public GetResponse clientGetById(String indexName, String id) {
        try {
            GetRequest getRequest = new GetRequest(indexName, id);
            return highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Es get IOException Error:" + e.getMessage());
        }
        return null;
    }

    /**
     * 获取Get部分字段
     *
     * @param indexName 索引名称
     * @param id        id
     * @param includes  获取的字段
     * @param excludes  排除的字段
     * @return
     */
    public GetResponse clientGetPartFields(String indexName, String id, String[] includes, String[] excludes) {
        try {
            GetRequest getRequest = new GetRequest(indexName, id);
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
            getRequest.fetchSourceContext(fetchSourceContext);
            return highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Es get IOException Error:" + e.getMessage());
        }
        return null;
    }

    /**
     * 获取get请求内容
     *
     * @param indexName           索引名称
     * @param id                  id
     * @param getResponseListener 监听器
     */
    public void clientGetAsync(String indexName, String id, GetResponseListener getResponseListener) {
        GetRequest getRequest = new GetRequest(indexName, id);
        highLevelClient.getAsync(getRequest, RequestOptions.DEFAULT, getResponseListener);
    }


    /**
     * 创建索引内容
     *
     * @param createIndexRequest
     * @return
     */
    public CreateIndexResponse clientCreateIndex(CreateIndexRequest createIndexRequest) {
        try {
            highLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("create index Exception：" + e.getMessage());
        }
        return null;
    }

    /**
     * 获取索引内容
     *
     * @param getIndexRequest
     * @return
     */
    public GetIndexResponse clientGetIndex(GetIndexRequest getIndexRequest) {
        try {
            highLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("get index Exception：" + e.getMessage());
        }
        return null;
    }

}
