package com.elasticsearch.demo.service.base;

import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.index.reindex.BulkByScrollResponse;

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
    boolean existIndex(GetIndexRequest getIndexRequest);

    /**
     * 删除索引
     *
     * @param deleteIndexRequest 删除索引请求
     * @return
     */
    boolean deleteIndex(DeleteIndexRequest deleteIndexRequest);


    /**
     * 修改index Mapping信息
     *
     * @param putMappingRequest
     * @return
     */
    boolean putIndexMapping(PutMappingRequest putMappingRequest);

    /**
     * 索引别名操作
     *
     * @param indices   索引
     * @param aliasName 索引别名
     * @param type      索引别名操作类型
     * @return
     */
    boolean indexAlias(String indices, String aliasName, IndicesAliasesRequest.AliasActions.Type type);


    /**
     * 改变索引
     *
     * @param oldIndexName 原索引名称
     * @param newIndexName 新索引名称
     * @return
     */
    BulkByScrollResponse reIndex(String oldIndexName, String newIndexName);
}
