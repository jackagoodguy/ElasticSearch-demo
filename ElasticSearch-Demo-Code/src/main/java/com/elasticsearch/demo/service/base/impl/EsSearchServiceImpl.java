package com.elasticsearch.demo.service.base.impl;

import com.elasticsearch.demo.common.Constant;
import com.elasticsearch.demo.service.base.EsSearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * 搜索服务
 *
 * @Author: ShayLau
 * @Date: 2020/8/21 9:51
 */
public class EsSearchServiceImpl implements EsSearchService {

    public void search(){

        String indexName= Constant.DEFAULT_ES_INDEX_NAME;
        SearchRequest searchRequest=new SearchRequest(indexName);

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchRequest.routing();
    }
}
