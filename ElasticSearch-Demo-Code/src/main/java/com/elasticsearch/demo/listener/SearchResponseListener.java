package com.elasticsearch.demo.listener;

import org.elasticsearch.action.search.SearchResponse;

/**
 * SearchResponseListener
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:07
 */
public class SearchResponseListener extends BaseResponseListener<SearchResponse> {
    @Override
    public void onResponse(SearchResponse searchResponse) {
        setResponseData(searchResponse);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
