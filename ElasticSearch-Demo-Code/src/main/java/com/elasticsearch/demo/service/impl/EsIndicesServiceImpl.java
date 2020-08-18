package com.elasticsearch.demo.service.impl;

import com.elasticsearch.demo.service.EsBaseService;
import com.elasticsearch.demo.service.EsIndexService;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.springframework.stereotype.Service;

/**
 * @Author: ShayLau
 * @Date: 2020/8/17 14:21
 */
@Service
public class EsIndicesServiceImpl extends EsBaseService implements EsIndexService {

    @Override
    public CreateIndexResponse createIndex(CreateIndexRequest createIndexRequest) {
        return clientCreateIndex(createIndexRequest);
    }

    @Override
    public GetIndexResponse getIndex(GetIndexRequest getIndexRequest) {
        return clientGetIndex(getIndexRequest);
    }

    @Override
    public Boolean existIndex(GetIndexRequest getIndexRequest) {
        return clientExistIndex(getIndexRequest);
    }
}
