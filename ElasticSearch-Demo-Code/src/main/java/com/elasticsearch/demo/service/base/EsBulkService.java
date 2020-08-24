package com.elasticsearch.demo.service.base;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;

/**
 * @Author: ShayLau
 * @Date: 2020/8/24 14:17
 */
public interface EsBulkService {

    /**
     * 批量操作
     *
     * @param bulkRequest
     * @return
     */
    BulkResponse bulkOperation(BulkRequest bulkRequest);

    /**
     * 异步批量操作
     *
     * @param bulkRequest
     */
    void bulkAsyncOperation(BulkRequest bulkRequest);
}
