package com.elasticsearch.demo.service.base.impl;

import com.elasticsearch.demo.listener.BulkResponseListener;
import com.elasticsearch.demo.service.base.EsBaseService;
import com.elasticsearch.demo.service.base.EsBulkService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RequestOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 批量操作
 *
 * @Author: ShayLau
 * @Date: 2020/8/24 14:19
 */
@Service
@Slf4j
public class EsBulkServiceImpl extends EsBaseService implements EsBulkService {

    /**
     * 同步执行批量操作
     *
     * @param bulkRequest
     * @return
     */
    @Override
    public BulkResponse bulkOperation(BulkRequest bulkRequest) {
        try {
            return highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("bulk exception:" + e.getMessage());
            return null;
        }
    }

    /**
     * 异步执行批量操作
     *
     * @param bulkRequest 需要执行的操作
     */
    @Override
    public void bulkAsyncOperation(BulkRequest bulkRequest) {
        highLevelClient.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new BulkResponseListener());
    }
}
