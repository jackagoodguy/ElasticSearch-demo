package com.elasticsearch.demo.service.base;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;

/**
 * Es基础服务
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 13:45
 */
public interface EsSearchService {


    /**
     * 普通查询
     *
     * @param searchRequest
     * @return
     */
    SearchResponse search(SearchRequest searchRequest);


    /**
     * 游标查询
     *
     * <description>
     * 相比较普通翻页，优点 翻页效率高因为使用了游标
     * 缺点：因为使用游标，设置了TimeValue,所以数据实时性较差
     * </description>
     *
     * @param scrollRequest 游标请求
     * @return
     */
    SearchResponse scrollSearch(SearchScrollRequest scrollRequest);

}
