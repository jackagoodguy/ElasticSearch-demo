package com.elasticsearch.demo.service.base.impl;

import com.elasticsearch.demo.common.Constant;
import com.elasticsearch.demo.service.base.EsBaseService;
import com.elasticsearch.demo.service.base.EsSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.annotation.Obsolete;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 搜索服务
 *
 * @Author: ShayLau
 * @Date: 2020/8/21 9:51
 */
@Service
@Slf4j
public class EsSearchServiceImpl extends EsBaseService implements EsSearchService {

    /**
     * 查询
     *
     * @param searchRequest
     * @return
     */
    @Override
    public SearchResponse search(SearchRequest searchRequest) {
        try {
            SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("search scroll exception:" + e.getMessage());
            return null;
        }
    }

    /**
     * 游标查询
     *
     * @param scrollRequest 游标请求
     * @return
     */
    @Override
    public SearchResponse scrollSearch(SearchScrollRequest scrollRequest) {
        try {
            SearchResponse searchResponse = highLevelClient.scroll(scrollRequest, RequestOptions.DEFAULT);
            return searchResponse;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("search scroll exception:" + e.getMessage());
            return null;
        }
    }
}
