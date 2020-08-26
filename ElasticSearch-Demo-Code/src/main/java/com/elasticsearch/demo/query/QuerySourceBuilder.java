package com.elasticsearch.demo.query;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.*;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;

import java.util.Optional;

/**
 * Es Source Query params set
 * <p>
 * 具体查询设置，需根据具体业务情况设置，代码只作为示例
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
     * 查询数量，默认10
     *
     * @param size 数据量
     * @return
     */
    public QuerySourceBuilder size(int size) {
        searchSourceBuilder.size(size);
        return this;
    }


    /**
     * 偏移量
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
        SortBuilder sortBuilder = SortBuilders.scoreSort();
        Optional.ofNullable(sortOrder).ifPresent(sortBuilder::order);
        searchSourceBuilder.sort(sortBuilder);
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
        SortBuilder<FieldSortBuilder> fieldSortBuilder = new FieldSortBuilder(fieldName);
        Optional.ofNullable(sortOrder).ifPresent(fieldSortBuilder::order);
        ///另一种构造方法
        //searchSourceBuilder.sort(fieldName, sortOrder);
        searchSourceBuilder.sort(fieldSortBuilder);
        return this;
    }

    /**
     * 高亮显示
     *
     * @param fieldName 字段名称
     * @return
     */
    public QuerySourceBuilder highlighter(String fieldName) {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field field = new HighlightBuilder.Field(fieldName);
        highlightBuilder.field(field);
        searchSourceBuilder.highlighter(highlightBuilder);
        return this;
    }


    ///聚合查询

    ///建议查询

    /**
     * 字段搜索建议
     *
     * @return
     */
    public QuerySourceBuilder fieldSuggest(String fieldName, String fieldValue) {
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        TermSuggestionBuilder fieldSuggest = SuggestBuilders.termSuggestion(fieldName);
        fieldSuggest.text(fieldValue);
        suggestBuilder.addSuggestion(fieldName, fieldSuggest);
        searchSourceBuilder.suggest(suggestBuilder);
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
