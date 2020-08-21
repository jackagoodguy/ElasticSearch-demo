package com.elasticsearch.demo.service.base.impl;

import com.elasticsearch.demo.service.base.EsBaseService;
import com.elasticsearch.demo.service.base.EsIndexService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.ReindexRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 索引服务
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 14:21
 */
@Service
@Slf4j
public class EsIndicesServiceImpl extends EsBaseService implements EsIndexService {

    @Override
    public GetIndexResponse getIndex(GetIndexRequest getIndexRequest) {
        try {
            return highLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("get index Exception：" + e.getMessage());
        }
        return null;
    }

    @Override
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

    @Override
    public boolean existIndex(GetIndexRequest getIndexRequest) {
        IndicesClient indicesClient = highLevelClient.indices();
        try {
            return indicesClient.exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("exist index Exception：" + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean deleteIndex(DeleteIndexRequest deleteIndexRequest) {
        IndicesClient indicesClient = highLevelClient.indices();
        try {
            AcknowledgedResponse acknowledgedResponse = indicesClient.delete(deleteIndexRequest, RequestOptions.DEFAULT);
            return acknowledgedResponse.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("create index Exception：" + e.getMessage());
            return false;
        }
    }


    @Override
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

    @Override
    public boolean indexAlias(String indices, String aliasName, IndicesAliasesRequest.AliasActions.Type type) {
        IndicesAliasesRequest indicesAliasesRequest = new IndicesAliasesRequest();
        IndicesAliasesRequest.AliasActions aliasActions = null;
        if (IndicesAliasesRequest.AliasActions.Type.ADD == type) {
            aliasActions = IndicesAliasesRequest.AliasActions.add();
        } else if (IndicesAliasesRequest.AliasActions.Type.REMOVE == type) {
            aliasActions = IndicesAliasesRequest.AliasActions.remove();
        }
        aliasActions.index(indices);
        aliasActions.alias(aliasName);
        indicesAliasesRequest.addAliasAction(aliasActions);
        try {
            AcknowledgedResponse acknowledgedResponse = highLevelClient.indices().updateAliases(indicesAliasesRequest, RequestOptions.DEFAULT);
            return acknowledgedResponse.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BulkByScrollResponse reIndex(String oldIndexName, String newIndexName) {
        ReindexRequest reindexRequest = new ReindexRequest();
        reindexRequest.setSourceIndices(oldIndexName).setDestIndex(newIndexName);
        try {
            return highLevelClient.reindex(reindexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
