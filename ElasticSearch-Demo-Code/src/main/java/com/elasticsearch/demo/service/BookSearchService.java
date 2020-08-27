package com.elasticsearch.demo.service;

import com.elasticsearch.demo.model.Book;
import com.elasticsearch.demo.model.BookScrollSearchBO;
import com.elasticsearch.demo.query.QuerySource;
import org.elasticsearch.action.get.GetResponse;

import java.util.List;

/**
 * 书本查询服务类
 *
 * @Author: ShayLau
 * @Date: 2020/8/27 11:22
 */
public interface BookSearchService {


    /**
     * 根据书本id获取文档响应信息
     *
     * @param id 文档id
     * @return
     */
    GetResponse getId(String id);

    /**
     * 使用游标Scroll查询书本
     *
     * @param scrollId scrollId
     * @return
     */
    BookScrollSearchBO bookScrollSearch(String scrollId);

    /**
     * 书本查询
     *
     * @return
     */
    List<Book> bookSearch();

    /**
     * 条件查询
     *
     * @param querySource
     * @return
     */
    List<Book> conditionSearch(QuerySource querySource);

    /**
     * 书本标题建议查询
     *
     * @param fieldName  字段名称
     * @param fieldValue 字段内容
     * @return
     */
    List<String> fieldSuggestSearch(String fieldName, String fieldValue);

    /**
     * 书本bool查询
     *
     * @return
     */
    List<Book> boolSearch();
}
