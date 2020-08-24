package com.elasticsearch.demo.service.base.impl;

import com.elasticsearch.demo.service.base.EsBaseService;
import com.elasticsearch.demo.service.base.EsSearchScrollService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: ShayLau
 * @Date: 2020/8/24 16:28
 */
@Service
@Slf4j
public class EsSearchScrollServiceImpl extends EsBaseService implements EsSearchScrollService {

    /**
     * 卷轴查询
     *
     * @param searchScrollRequest 卷轴查询请求
     * @return
     */
    @Override
    public SearchResponse searchScroll(SearchScrollRequest searchScrollRequest) {
        try {
            SearchResponse searchResponse = highLevelClient.scroll(searchScrollRequest, RequestOptions.DEFAULT);
            return searchResponse;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("search scroll exception:" + e.getMessage());
            return null;
        }
    }

}
