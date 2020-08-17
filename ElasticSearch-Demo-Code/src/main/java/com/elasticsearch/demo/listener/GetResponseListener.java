package com.elasticsearch.demo.listener;

import org.elasticsearch.action.get.GetResponse;

/**
 * GetResponseListener
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 9:56
 */
public class GetResponseListener extends BaseResponseListener<GetResponse> {

    @Override
    public void onResponse(GetResponse documentFields) {
        setResponseData(documentFields);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
