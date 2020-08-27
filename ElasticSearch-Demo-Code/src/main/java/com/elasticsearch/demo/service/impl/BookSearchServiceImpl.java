package com.elasticsearch.demo.service.impl;

import com.elasticsearch.demo.model.Book;
import com.elasticsearch.demo.model.BookScrollSearchBO;
import com.elasticsearch.demo.query.QuerySourceBuilder;
import com.elasticsearch.demo.service.BookSearchService;
import com.elasticsearch.demo.service.base.EsBaseService;
import com.elasticsearch.demo.service.base.EsGetService;
import com.elasticsearch.demo.service.base.EsSearchScrollService;
import com.elasticsearch.demo.service.base.EsSearchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: ShayLau
 * @Date: 2020/8/27 11:22
 */
@Service
@Slf4j
public class BookSearchServiceImpl extends EsBaseService implements BookSearchService {


    @Autowired
    private EsSearchScrollService searchScrollService;
    @Autowired
    private EsSearchService searchService;
    @Autowired
    private EsGetService getService;

    /**
     * 使用游标Scroll查询书本
     *
     * @param scrollId scrollId
     * @return
     */
    @Override
    public BookScrollSearchBO bookScrollSearch(String scrollId) {
        BookScrollSearchBO bookScrollSearchBO = new BookScrollSearchBO();
        SearchResponse searchResponse;
        if (StringUtils.isNotBlank(scrollId)) {
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
            searchScrollRequest.scroll(TimeValue.timeValueMillis(10));
            searchResponse = searchScrollService.searchScroll(searchScrollRequest);
        } else {
            SearchRequest searchRequest = new SearchRequest(defaultBookIndex);
            searchRequest.scroll(new Scroll(TimeValue.timeValueMillis(10)));
            searchResponse = searchService.search(searchRequest);
        }
        List<String> result = new ArrayList<>();
        bookScrollSearchBO.setScrollId(searchResponse.getScrollId());
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        if (searchHits.length > 0) {
            Arrays.asList(searchHits).stream().forEach(searchHit -> result.add(searchHit.getSourceAsString()));
        }
        Gson gson = new GsonBuilder().create();
        bookScrollSearchBO.setBookList(result.stream().map(str -> gson.fromJson(str, Book.class)).collect(Collectors.toList()));
        return bookScrollSearchBO;
    }

    @Override
    public List<Book> bookSearch() {
        SearchRequest searchRequest = new SearchRequest(defaultBookIndex);
        QuerySourceBuilder querySourceBuilder = new QuerySourceBuilder();
        querySourceBuilder.size(1000);
        return getBooks(querySourceBuilder, searchRequest);
    }


    @Override
    public List<Book> conditionSearch(QuerySourceBuilder querySourceBuilder) {
        SearchRequest searchRequest = new SearchRequest(defaultBookIndex);
        return getBooks(querySourceBuilder, searchRequest);
    }

    private List<Book> getBooks(QuerySourceBuilder querySourceBuilder, SearchRequest searchRequest) {
        searchRequest.source(querySourceBuilder.build());
        List<String> result = new ArrayList<>();
        SearchResponse searchResponse = searchService.search(searchRequest);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        if (searchHits.length > 0) {
            Arrays.asList(searchHits).stream().forEach(searchHit -> result.add(searchHit.getSourceAsString()));
        }
        Gson gson = new GsonBuilder().create();
        return result.stream().map(str -> gson.fromJson(str, Book.class)).collect(Collectors.toList());
    }


    @Override
    public List<String> fieldSuggestSearch(String fieldName, String fieldValue) {
        SearchRequest searchRequest = new SearchRequest(defaultBookIndex);
        QuerySourceBuilder querySourceBuilder = new QuerySourceBuilder();
        querySourceBuilder.fieldSuggest(fieldName, fieldValue);
        searchRequest.source(querySourceBuilder.build());
        SearchResponse searchResponse = searchService.search(searchRequest);
        Suggest suggestions = searchResponse.getSuggest();
        TermSuggestion termSuggestion = suggestions.getSuggestion(fieldName);
        return termSuggestion.getEntries().stream().map(Suggest.Suggestion.Entry::getText).map(Text::toString).collect(Collectors.toList());
    }


    /**
     * 根据书本id获取文档响应信息
     *
     * @param id 文档id
     * @return
     */
    @Override
    public GetResponse getId(String id) {
        return getService.getById(defaultBookIndex, id);
    }
}
