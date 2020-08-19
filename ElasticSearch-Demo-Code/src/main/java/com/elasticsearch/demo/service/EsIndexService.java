package com.elasticsearch.demo.service;

import org.elasticsearch.client.indices.*;

/**
 * @Author: ShayLau
 * @Date: 2020/8/17 13:50
 */
public interface EsIndexService {


    /**
     * 获取索引信息
     *
     * @param getIndexRequest
     * @return
     */
    GetIndexResponse getIndex(GetIndexRequest getIndexRequest);


    /**
     * 创建索引
     *
     * @param createIndexRequest
     * @return
     */
    CreateIndexResponse createIndex(CreateIndexRequest createIndexRequest);


    /**
     * 是否存在索引
     *
     * @param getIndexRequest
     * @return
     */
    Boolean existIndex(GetIndexRequest getIndexRequest);

    /**
     * 修改index Mapping信息
     *
     * @param putMappingRequest
     * @return
     */
    boolean putIndexMapping(PutMappingRequest putMappingRequest);

}
