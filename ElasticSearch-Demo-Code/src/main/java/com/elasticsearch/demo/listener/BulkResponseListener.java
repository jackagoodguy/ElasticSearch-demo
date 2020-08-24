package com.elasticsearch.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkResponse;

/**
 * 批量操作监听
 *
 * @Author: ShayLau
 * @Date: 2020/8/24 15:22
 */
@Slf4j
public class BulkResponseListener extends BaseResponseListener<BulkResponse> {
    @Override
    public void onResponse(BulkResponse bulkItemResponses) {
        System.out.println(bulkItemResponses);
        log.info("bulk request execute completion!");
    }

    @Override
    public void onFailure(Exception e) {
        e.printStackTrace();
        log.error("bulk request exception：" + e.getMessage());
    }
}
