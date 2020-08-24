package com.elasticsearch.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.update.UpdateResponse;

/**
 * Update Response Listener
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:02
 */
@Slf4j
public class UpdateResponseListener extends BaseResponseListener<UpdateResponse> {
    @Override
    public void onResponse(UpdateResponse updateResponse) {
        setResponseData(updateResponse);
        log.info("update request execute completion!");
    }

    @Override
    public void onFailure(Exception e) {
        e.printStackTrace();
        log.error("update request exception：" + e.getMessage());
    }
}
