package com.elasticsearch.demo.listener;

import org.elasticsearch.action.update.UpdateResponse;

/**
 * UpdateResponseListener
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:02
 */
public class UpdateResponseListener extends BaseResponseListener<UpdateResponse> {
    @Override
    public void onResponse(UpdateResponse updateResponse) {
        setResponseData(updateResponse);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
