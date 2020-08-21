package com.elasticsearch.demo.service.impl;


import com.elasticsearch.demo.common.Constant;
import com.elasticsearch.demo.enums.EsIndexFieldTypeEnum;
import com.elasticsearch.demo.model.indexmapping.BaseMapping;
import com.elasticsearch.demo.model.indexmapping.BookMapping;
import com.elasticsearch.demo.model.indexmapping.MappingFieldProperties;
import com.elasticsearch.demo.service.BookService;
import com.elasticsearch.demo.service.base.EsGetService;
import com.elasticsearch.demo.service.base.EsIndexService;
import com.elasticsearch.demo.service.base.EsTermVectorsService;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 书本服务
 *
 * @Author: ShayLau
 * @Date: 2020/8/18 17:09
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private EsIndexService indexService;
    @Autowired
    private EsGetService getService;
    @Autowired
    private EsTermVectorsService termVectorsService;

    /**
     * 书本索引名称
     */
    private String bookIndexName = Constant.DEFAULT_ES_INDEX_NAME;

    /**
     * 设置书本索引名称
     *
     * @param bookIndexName
     */
    private void setBookIndexName(String bookIndexName) {
        if (StringUtils.isNotBlank(bookIndexName)) {
            this.bookIndexName = bookIndexName;
        }
    }

    /**
     * 创建书本索引
     *
     * @param mapping mapping配置
     * @return
     */
    @Override
    public boolean createBookIndex(String mapping) {
        GetIndexRequest getIndexRequest = new GetIndexRequest(Constant.DEFAULT_ES_INDEX_NAME);
        boolean existIndex = indexService.existIndex(getIndexRequest);
        if (!existIndex) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(Constant.DEFAULT_ES_INDEX_NAME);
            if (StringUtils.isNotBlank(mapping)) {
                createIndexRequest.mapping(mapping, XContentType.JSON);
            } else {
                createIndexRequest.mapping(BookService.defaultIndexMapping(), XContentType.JSON);
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
        GetIndexRequest getIndexRequest = new GetIndexRequest(bookIndexName);
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
        deleteIndexRequest.indices(bookIndexName);
        return indexService.deleteIndex(deleteIndexRequest);
    }

    /**
     * 根据书本id获取文档响应信息
     *
     * @param id 文档id
     * @return
     */
    @Override
    public GetResponse getId(String id) {
        return getService.getById(bookIndexName, id);
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
        setBookIndexName(bookIndexName);
        return indexService.indexAlias(this.bookIndexName, aliasName, type);
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
        PutMappingRequest putMappingRequest = new PutMappingRequest(bookIndexName);
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
        TermVectorsRequest termVectorsRequest = new TermVectorsRequest(bookIndexName, id);
        ///词向量相关查询配置省略...
        return termVectorsService.getTermVector(termVectorsRequest);
    }

    @Override
    public void reBookIndex(String oldIndexName, String newIndexName) {
        setBookIndexName(oldIndexName);
        BulkByScrollResponse response = indexService.reIndex(bookIndexName, newIndexName);
        System.out.println(response.getStatus());
    }
}
