package com.elasticsearch.demo.service;

import com.elasticsearch.demo.listener.GetResponseListener;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Es基本服务类
 *
 * <description>
 * 处理基本的Es服务
 * </description>
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:31
 */
@Service
@Slf4j
public class EsBaseService {

    @Autowired
    private RestHighLevelClient highLevelClient;


    public GetResponse getById(String indexName, String id) {
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
    public GetResponse getPartField(String indexName, String id, String[] includes, String[] excludes) {
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
    public void getByListener(String indexName, String id, GetResponseListener getResponseListener) {
        GetRequest getRequest = new GetRequest(indexName, id);
        highLevelClient.getAsync(getRequest, RequestOptions.DEFAULT, getResponseListener);
    }


    /**
     * 获取词向量信息
     *
     * @param termVectorsRequest
     * @return
     */
    public TermVectorsResponse getTermVector(TermVectorsRequest termVectorsRequest) {
        try {
            highLevelClient.termvectors(termVectorsRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("get termVectors Exception：" + e.getMessage());
        }
        return null;
    }


    /**
     * 获取索引
     *
     * @param getIndexRequest
     * @return
     */
    public GetIndexResponse getIndex(GetIndexRequest getIndexRequest) {
        try {
            return highLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("get index Exception：" + e.getMessage());
        }
        return null;
    }

    /**
     * 创建索引
     *
     * @param createIndexRequest
     * @return
     */
    public CreateIndexResponse createIndex(CreateIndexRequest createIndexRequest) {
        IndicesClient indicesClient = highLevelClient.indices();
        try {
            return indicesClient.create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("create index Exception：" + e.getMessage());
        }
        return null;
    }

    /**
     * 删除索引
     *
     * @param deleteIndexRequest
     * @return
     */
    public AcknowledgedResponse deleteIndex(DeleteIndexRequest deleteIndexRequest) {
        IndicesClient indicesClient = highLevelClient.indices();
        try {
            return indicesClient.delete(deleteIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("create index Exception：" + e.getMessage());
        }
        return null;
    }

    /**
     * 是否存在索引
     *
     * @param getIndexRequest
     * @return
     */
    public Boolean existIndex(GetIndexRequest getIndexRequest) {
        IndicesClient indicesClient = highLevelClient.indices();
        try {
            return indicesClient.exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("exist index Exception：" + e.getMessage());
        }
        return null;
    }

    /**
     * 更新索引Mapping
     *
     * @param putMappingRequest
     * @return
     */
    public boolean putIndexMapping(PutMappingRequest putMappingRequest) {
        IndicesClient indicesClient = highLevelClient.indices();
        try {
            AcknowledgedResponse acknowledgedResponse = indicesClient.putMapping(putMappingRequest, RequestOptions.DEFAULT);
            return acknowledgedResponse.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("exist index Exception：" + e.getMessage());
            return false;
        }
    }


}
