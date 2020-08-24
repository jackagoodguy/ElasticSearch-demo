package com.elasticsearch.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteResponse;

/**
 * Delete Response Listener
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:00
 */
@Slf4j
public class DeleteResponseListener extends BaseResponseListener<DeleteResponse> {
    @Override
    public void onResponse(DeleteResponse deleteResponse) {
        setResponseData(deleteResponse);
        log.info("delete request execute completion!");
    }

    @Override
    public void onFailure(Exception e) {
        e.printStackTrace();
        log.error("delete request exception：" + e.getMessage());
    }
}
