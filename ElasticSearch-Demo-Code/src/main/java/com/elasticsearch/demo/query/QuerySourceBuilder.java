package com.elasticsearch.demo.query;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

/**
 * Es查询资源设置
 *
 * @Author: ShayLau
 * @Date: 2020/8/25 13:31
 */
public class QuerySourceBuilder {

    private SearchSourceBuilder searchSourceBuilder;

    public QuerySourceBuilder() {
        searchSourceBuilder = new SearchSourceBuilder();
    }

    /**
     * 获取Es资源查询构建对象
     *
     * @return
     */
    public SearchSourceBuilder build() {
        return searchSourceBuilder;
    }


    /**
     * 匹配查询
     *
     * @param size 数据量
     * @return
     */
    public QuerySourceBuilder size(int size) {
        searchSourceBuilder.size(size);
        return this;
    }


    /**
     * 匹配查询
     *
     * @param offset 偏移量
     * @return
     */
    public QuerySourceBuilder offset(int offset) {
        searchSourceBuilder.from(offset);
        return this;
    }


    /**
     * 匹配查询
     *
     * @param fieldName 字段名称
     * @param value     字段值
     * @return
     */
    public QuerySourceBuilder matchQuery(String fieldName, Object value) {
        searchSourceBuilder.query(QueryBuilders.termQuery(fieldName, value));
        return this;
    }

    /**
     * 匹配所有查询
     *
     * @return
     */
    public QuerySourceBuilder matchAllQuery() {
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        return this;
    }


    /**
     * 相关性排序
     *
     * @param sortOrder 排序
     * @return
     */
    public QuerySourceBuilder scoreScore(SortOrder sortOrder) {
        searchSourceBuilder.sort(ScoreSortBuilder.NAME, sortOrder);
        return this;
    }


    /**
     * 字段排序
     *
     * @param fieldName 字段名称
     * @param sortOrder 排序
     * @return
     */
    public QuerySourceBuilder fieldSort(String fieldName, SortOrder sortOrder) {
        searchSourceBuilder.sort(fieldName, sortOrder);
        return this;
    }

    /**
     * 高亮显示
     *
     * @return
     */
    public QuerySourceBuilder highlighter() {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        searchSourceBuilder.highlighter(highlightBuilder);
        return this;
    }

    /**
     * bool 查询
     *
     * @return
     */
    public QuerySourceBuilder boolQuery(BoolQueryBuilder boolQueryBuilder) {
        searchSourceBuilder.query(boolQueryBuilder);
        return this;
    }

    /**
     * 相关性分值设置
     *
     * @return
     */
    public QuerySourceBuilder scoreQuery(BoolQueryBuilder boolQueryBuilder) {
        return this;
    }


}
