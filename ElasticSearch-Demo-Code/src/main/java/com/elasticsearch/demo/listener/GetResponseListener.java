package com.elasticsearch.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetResponse;

/**
 * Get Response Listener
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 9:56
 */
@Slf4j
public class GetResponseListener extends BaseResponseListener<GetResponse> {

    @Override
    public void onResponse(GetResponse documentFields) {
        setResponseData(documentFields);
        log.info("get request execute completion!");

    }

    @Override
    public void onFailure(Exception e) {
        e.printStackTrace();
        log.error("get request exception：" + e.getMessage());
    }
}
