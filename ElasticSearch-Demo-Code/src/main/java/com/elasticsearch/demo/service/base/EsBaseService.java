package com.elasticsearch.demo.service.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elasticsearch.demo.common.Constant;
import com.elasticsearch.demo.model.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Es基本服务类
 *
 * <description>
 * 处理基本的Es服务
 * </description>
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:31
 */
@Service
@Slf4j
public class EsBaseService<M, T> extends ServiceImpl {

    @Autowired
    public RestHighLevelClient highLevelClient;


    /**
     * 索引名称
     */
    public final String defaultBookIndex = Constant.DEFAULT_ES_INDEX_NAME;

    /**
     * 设置书本索引名称
     *
     * @param indexName 给定索引名称
     */
    public String getBookIndexName(String indexName) {
        return StringUtils.isNotBlank(indexName) ? indexName : defaultBookIndex;
    }


    /**
     * 获取响应字符串
     *
     * @param searchResponse
     * @return
     */
    public List<String> getResponseList(SearchResponse searchResponse) {
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        if (searchHits.length > 0) {
            return Arrays.asList(searchHits).stream().map(searchHit -> searchHit.getSourceAsString()).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

}
