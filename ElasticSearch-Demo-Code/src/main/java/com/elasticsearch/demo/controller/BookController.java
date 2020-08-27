package com.elasticsearch.demo.controller;

import com.elasticsearch.demo.common.ResultBody;
import com.elasticsearch.demo.query.QuerySource;
import com.elasticsearch.demo.service.BookSearchService;
import com.elasticsearch.demo.service.BookIndexService;
import com.google.gson.Gson;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.get.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 书本查询控制器
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:12
 */
@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookIndexService bookIndexService;
    @Autowired
    private BookSearchService bookSearchService;

    /**
     * 创建书本索引
     *
     * @param mapping mapping配置
     * @return
     */
    @PostMapping("/index")
    public ResultBody createBookIndex(@RequestParam(required = false) String mapping) {
        return ResultBody.success(bookIndexService.createBookIndex(mapping));
    }

    /**
     * 获取索引
     *
     * @return
     */
    @GetMapping("/index")
    public ResultBody getIndex() {
        return ResultBody.success(new Gson().toJson(bookIndexService.getBookIndex()));
    }


    /**
     * 删除书本索引
     *
     * @return
     */
    @DeleteMapping("/index")
    public ResultBody DeleteIndex() {
        return ResultBody.success(bookIndexService.deleteBookIndex());
    }


    /**
     * 修改索引Mapping信息
     *
     * <description>
     * 只作为测试Mapping相关接口使用
     * <p>
     * 具体Mapping配置根据业务查看相关文档配置
     * <p>
     * 该方法只支持index索引的field未设置的Mapping才能够执行,比如:Mapping中字段title的type已经设置(type:text),则不允许修改field的type
     * <p>
     * 测试例子中设置的title:true,在创建索引接口中我们已经设置了book索引title的mapping，所以该例子会报错500
     *
     * </description>
     *
     * @return
     */
    @PutMapping("/index/mapping")
    public ResultBody putIndexMapping() {
        return ResultBody.success(bookIndexService.putIndexMapping());
    }


    /**
     * 添加书本索引别名
     *
     * @param bookIndexName 书本索引名称
     * @param aliasName     别名
     * @return
     */
    @PostMapping("/index/alias")
    public ResultBody addBookIndexAlias(@RequestParam(required = false) String bookIndexName, @RequestParam String aliasName) {
        return ResultBody.success(bookIndexService.bookIndexAlias(bookIndexName, aliasName, IndicesAliasesRequest.AliasActions.Type.ADD));
    }


    /**
     * 删除书本索引别名
     *
     * @param bookIndexName 书本索引名称
     * @param aliasName     别名
     * @return
     */
    @DeleteMapping("/index/alias")
    public ResultBody removeBookIndexAlias(@RequestParam(required = false) String bookIndexName, @RequestParam String aliasName) {
        return ResultBody.success(bookIndexService.bookIndexAlias(bookIndexName, aliasName, IndicesAliasesRequest.AliasActions.Type.REMOVE));
    }


    /**
     * 复制索引信息
     *
     * <description>
     * 索引受文件系统的限制。仅可能为小写字母，不能下划线开头,具体查看官网说明
     * </description>
     *
     * @param oldIndexName 原索引名称
     * @param newIndexName 新索引名称
     * @return
     */
    @PostMapping("/reIndex")
    public ResultBody reBookIndex(@RequestParam String oldIndexName, @RequestParam String newIndexName) {
        bookIndexService.reBookIndex(oldIndexName.toLowerCase(), newIndexName.toLowerCase());
        return ResultBody.success();
    }

    /**
     * 扫描书本表数据到指定Es索引
     *
     * @param bookIndexName 书本索引名称
     * @return
     */
    @PostMapping("/scan")
    public ResultBody scanDbToEs(@RequestParam(required = false) String bookIndexName) {
        bookIndexService.scanDbToEs(bookIndexName);
        return ResultBody.success();
    }


    /**
     * 获取书本索引文档信息
     *
     * @param id 书本索引id
     * @return
     */
    @GetMapping("")
    public ResultBody getDocumentById(@RequestParam String id) {
        GetResponse getResponse = bookSearchService.getId(id);
        return ResultBody.success(getResponse);
    }


    /**
     * 查询书本
     *
     * @return
     */
    @GetMapping("/search")
    public ResultBody searchBook() {
        return ResultBody.success(bookSearchService.bookSearch());
    }

    /**
     * 使用游标查询书本
     *
     * @param scrollId 游标id
     * @return
     */
    @GetMapping("/search/scroll")
    public ResultBody scrollSearchBook(@RequestParam(required = false) String scrollId) {
        return ResultBody.success(bookSearchService.bookScrollSearch(scrollId));
    }

    /**
     * 条件查询
     *
     * @return
     */
    @GetMapping("/search/condition")
    public ResultBody conditionSearch() {
        QuerySource querySource = new QuerySource();
        querySource.matchQuery("author", "John Burt Foster Jr.");
        return ResultBody.success(bookSearchService.conditionSearch(querySource));
    }

    /**
     * bool查询
     *
     * @return
     */
    @GetMapping("/search/bool")
    public ResultBody boolSearch() {
        return ResultBody.success(bookSearchService.boolSearch());
    }


    /**
     * 字段内容建议查询
     *
     * @return
     */
    @PostMapping("/field/suggest")
    public ResultBody fieldSuggest(String fieldName, String fieldValue) {
        return ResultBody.success(bookSearchService.fieldSuggestSearch(fieldName, fieldValue));
    }


}
