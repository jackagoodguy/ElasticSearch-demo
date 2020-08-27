package com.elasticsearch.demo.service.impl;


import com.elasticsearch.demo.enums.EsIndexFieldTypeEnum;
import com.elasticsearch.demo.mapper.BookMapper;
import com.elasticsearch.demo.model.Book;
import com.elasticsearch.demo.model.indexmapping.BaseMapping;
import com.elasticsearch.demo.model.indexmapping.BookMapping;
import com.elasticsearch.demo.model.indexmapping.MappingFieldProperties;
import com.elasticsearch.demo.service.BookIndexService;
import com.elasticsearch.demo.service.base.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 书本服务
 *
 * @Author: ShayLau
 * @Date: 2020/8/18 17:09
 */
@Service
public class BookIndexServiceImpl extends EsBaseService<BookMapper, Book> implements BookIndexService {

    @Autowired
    private EsIndexService indexService;
    @Autowired
    private EsTermVectorsService termVectorsService;
    @Autowired
    private EsBulkService bulkService;

    /**
     * 创建书本索引
     *
     * @param mapping mapping配置
     * @return
     */
    @Override
    public boolean createBookIndex(String mapping) {
        GetIndexRequest getIndexRequest = new GetIndexRequest(defaultBookIndex);
        boolean existIndex = indexService.existIndex(getIndexRequest);
        if (!existIndex) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(defaultBookIndex);
            if (StringUtils.isNotBlank(mapping)) {
                createIndexRequest.mapping(mapping, XContentType.JSON);
            } else {
                createIndexRequest.mapping(BookIndexService.defaultIndexMapping(), XContentType.JSON);
            }
            CreateIndexResponse createIndexResponse = indexService.createIndex(createIndexRequest);
            return createIndexResponse.isAcknowledged();
        }
        return false;
    }

    /**
     * 获取书本索引信息
     *
     * @return
     */
    @Override
    public GetIndexResponse getBookIndex() {
        GetIndexRequest getIndexRequest = new GetIndexRequest(defaultBookIndex);
        GetIndexResponse getIndexResponse = indexService.getIndex(getIndexRequest);
        return getIndexResponse;
    }

    /**
     * 删除书本索引
     *
     * @return
     */
    @Override
    public boolean deleteBookIndex() {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest();
        deleteIndexRequest.indices(defaultBookIndex);
        return indexService.deleteIndex(deleteIndexRequest);
    }


    /**
     * 书本索引别名处理
     *
     * @param bookIndexName 书本索引名称
     * @param aliasName     别名名称
     * @param type          别名操作类型
     * @return
     */
    @Override
    public boolean bookIndexAlias(String bookIndexName, String aliasName, IndicesAliasesRequest.AliasActions.Type type) {
        return indexService.indexAlias(getBookIndexName(bookIndexName), aliasName, type);
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
    @Override
    public boolean putIndexMapping() {
        PutMappingRequest putMappingRequest = new PutMappingRequest(defaultBookIndex);
        String mappingSource;

        ///设置index的mapping，该处只作为测试接口使用
        ///如果有接口需求，参照官方文档，通过前端接口传入JSON格式的mapping字符串
        ///然后调用 putMappingRequest.source(mappingSource, XContentType.JSON); 接口接口设置mapping信息

        BookMapping bookMapping = new BookMapping();
        bookMapping.setTitle(new MappingFieldProperties(EsIndexFieldTypeEnum.TEXT.getType(), true));
        BaseMapping baseMapping = new BaseMapping(bookMapping);
        Gson gson = new Gson();
        mappingSource = gson.toJson(baseMapping);

        putMappingRequest.source(mappingSource, XContentType.JSON);
        return indexService.putIndexMapping(putMappingRequest);
    }

    /**
     * 书本词向量信息查询
     *
     * @param id document id
     * @return
     */
    @Override
    public TermVectorsResponse bookTermVector(String id) {
        TermVectorsRequest termVectorsRequest = new TermVectorsRequest(defaultBookIndex, id);
        ///词向量相关查询配置省略...
        return termVectorsService.getTermVector(termVectorsRequest);
    }

    @Override
    public void reBookIndex(String oldIndexName, String newIndexName) {
        BulkByScrollResponse response = indexService.reIndex(getBookIndexName(oldIndexName), newIndexName);
        System.out.println(response.getStatus());
    }

    @Override
    public void scanDbToEs(String indexName) {
        List<Book> books = list();
        //书本索引名称
        String bookIndexName = getBookIndexName(indexName);
        //使用批量ES操作，添加MySQL数据导入Es
        BulkRequest bulkRequest = new BulkRequest();
        //使用GSON,转换Object对象为Json字符串
        Gson gson = new GsonBuilder().create();
        books.stream().forEach(book -> bulkRequest.add(new IndexRequest(bookIndexName).id(book.getIsbn())
                .source(gson.toJson(book), XContentType.JSON)));
        //异步执行数据批量导入
        bulkService.bulkAsyncOperation(bulkRequest);
    }


}
