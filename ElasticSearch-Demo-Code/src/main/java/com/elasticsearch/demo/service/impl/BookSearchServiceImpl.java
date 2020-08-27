package com.elasticsearch.demo.service.impl;

import com.elasticsearch.demo.model.Book;
import com.elasticsearch.demo.model.BookScrollSearchBO;
import com.elasticsearch.demo.query.QuerySource;
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
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private Gson gson = new GsonBuilder().create();


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
        bookScrollSearchBO.setScrollId(searchResponse.getScrollId());
        //书本响应信息
        List<String> responseResult = getResponseList(searchResponse);
        List<Book> bookList = responseResult.stream().map(str -> gson.fromJson(str, Book.class)).collect(Collectors.toList());
        bookScrollSearchBO.setBookList(bookList);
        return bookScrollSearchBO;
    }

    @Override
    public List<Book> bookSearch() {
        SearchRequest searchRequest = new SearchRequest(defaultBookIndex);
        QuerySource querySource = new QuerySource();
        querySource.size(1000);
        searchRequest.source(querySource.build());
        return getBooks(searchRequest);
    }


    @Override
    public List<Book> conditionSearch(QuerySource querySource) {
        SearchRequest searchRequest = new SearchRequest(defaultBookIndex);
        searchRequest.source(querySource.build());
        return getBooks(searchRequest);
    }


    @Override
    public List<String> fieldSuggestSearch(String fieldName, String fieldValue) {
        SearchRequest searchRequest = new SearchRequest(defaultBookIndex);
        QuerySource querySource = new QuerySource();
        querySource.fieldSuggest(fieldName, fieldValue);
        searchRequest.source(querySource.build());
        SearchResponse searchResponse = searchService.search(searchRequest);
        Suggest suggestions = searchResponse.getSuggest();
        TermSuggestion termSuggestion = suggestions.getSuggestion(fieldName);
        return termSuggestion.getEntries().stream().map(Suggest.Suggestion.Entry::getText).map(Text::toString).collect(Collectors.toList());
    }

    @Override
    public List<Book> boolSearch() {
        SearchRequest searchRequest = new SearchRequest(defaultBookIndex);
        QuerySource querySource = new QuerySource();
        //Bool查询
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.matchQuery("title", "中"));
        querySource.boolQuery(boolQueryBuilder);
        SearchSourceBuilder searchSourceBuilder = querySource.build();
        searchRequest.source(searchSourceBuilder);
        return getBooks(searchRequest);
    }


    /**
     * 获取书本信息
     *
     * @param searchRequest
     * @return
     */
    private List<Book> getBooks(SearchRequest searchRequest) {
        SearchResponse searchResponse = searchService.search(searchRequest);
        List<String> responseResult = getResponseList(searchResponse);
        if (responseResult.isEmpty()) {
            return null;
        }
        return responseResult.stream().map(str -> gson.fromJson(str, Book.class)).collect(Collectors.toList());
    }
}
