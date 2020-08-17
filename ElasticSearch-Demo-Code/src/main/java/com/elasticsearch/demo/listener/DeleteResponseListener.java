package com.elasticsearch.demo.listener;

import org.elasticsearch.action.delete.DeleteResponse;

/**
 * DeleteResponseListener
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:00
 */
public class DeleteResponseListener extends BaseResponseListener<DeleteResponse> {
    @Override
    public void onResponse(DeleteResponse deleteResponse) {
        setResponseData(deleteResponse);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
