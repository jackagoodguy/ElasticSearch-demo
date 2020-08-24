package com.elasticsearch.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;

/**
 * Index Response Listener
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:03
 */
@Slf4j
public class IndexResponseListener extends BaseResponseListener<IndexResponse> {
    @Override
    public void onResponse(IndexResponse indexResponse) {
        setResponseData(indexResponse);
        log.info("index request execute completion!");
    }

    @Override
    public void onFailure(Exception e) {
        e.printStackTrace();
        log.error("index request exception：" + e.getMessage());
    }
}
