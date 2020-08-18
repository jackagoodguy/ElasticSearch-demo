package com.elasticsearch.demo.service;

import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

/**
 * @Author: ShayLau
 * @Date: 2020/8/17 13:50
 */
public interface EsIndexService {

    /**
     * 创建索引
     *
     * @param createIndexRequest
     * @return
     */
    CreateIndexResponse createIndex(CreateIndexRequest createIndexRequest);


    /**
     * 获取索引信息
     *
     * @param getIndexRequest
     * @return
     */
    GetIndexResponse getIndex(GetIndexRequest getIndexRequest);

    /**
     * 是否存在索引
     *
     * @param getIndexRequest
     * @return
     */
    Boolean existIndex(GetIndexRequest getIndexRequest);


}
