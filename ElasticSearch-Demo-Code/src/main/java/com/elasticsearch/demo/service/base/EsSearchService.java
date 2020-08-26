package com.elasticsearch.demo.service.base;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;

/**
 * Es基础服务
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 13:45
 */
public interface EsSearchService {


    /**
     * 查询
     *
     * @param searchRequest
     * @return
     */
    SearchResponse search(SearchRequest searchRequest);

}
