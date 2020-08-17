package com.elasticsearch.demo.listener;

import org.elasticsearch.action.index.IndexResponse;

/**
 * IndexResponseListener
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:03
 */
public class IndexResponseListener extends BaseResponseListener<IndexResponse> {
    @Override
    public void onResponse(IndexResponse indexResponse) {
        setResponseData(indexResponse);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
