package com.elasticsearch.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;

/**
 * Search Response Listener
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:07
 */
@Slf4j
public class SearchResponseListener extends BaseResponseListener<SearchResponse> {
    @Override
    public void onResponse(SearchResponse searchResponse) {
        setResponseData(searchResponse);
        log.info("search request execute completion!");
    }

    @Override
    public void onFailure(Exception e) {
        e.printStackTrace();
        log.error("search request exception：" + e.getMessage());

    }
}
