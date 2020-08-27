package com.elasticsearch.demo.service;


import com.elasticsearch.demo.enums.EsIndexFieldTypeEnum;
import com.elasticsearch.demo.model.indexmapping.BaseMapping;
import com.elasticsearch.demo.model.indexmapping.BookMapping;
import com.elasticsearch.demo.model.indexmapping.MappingFieldProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.client.indices.GetIndexResponse;

/**
 * 书本服务
 *
 * @Author: ShayLau
 * @Date: 2020/8/18 17:08
 */
public interface BookIndexService {

    /**
     * 默认书本映射关系
     *
     * @return 书本映射关系JSON字符串
     */
    static String defaultIndexMapping() {
        BookMapping bookMapping = new BookMapping();
        //文本类型
        MappingFieldProperties textFieldProperties = new MappingFieldProperties(EsIndexFieldTypeEnum.TEXT.getType(), true);
        bookMapping.setAuthor(textFieldProperties);
        bookMapping.setChineseTitle(textFieldProperties);
        bookMapping.setDescription(textFieldProperties);
        bookMapping.setIsbn(textFieldProperties);
        bookMapping.setSubTitle(textFieldProperties);
        bookMapping.setTitle(textFieldProperties);
        BaseMapping baseMapping = new BaseMapping(bookMapping);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(baseMapping);
    }


    /**
     * 创建书本索引
     *
     * @param mapping mapping配置
     * @return
     */
    boolean createBookIndex(String mapping);

    /**
     * 获取书本索引信息
     *
     * @return
     */
    GetIndexResponse getBookIndex();

    /**
     * 删除书本索引
     *
     * @return
     */
    boolean deleteBookIndex();


    /**
     * 书本索引别名处理
     *
     * @param bookIndexName 书本索引名称
     * @param aliasName     别名名称
     * @param type          别名操作类型
     * @return
     */
    boolean bookIndexAlias(String bookIndexName, String aliasName, IndicesAliasesRequest.AliasActions.Type type);


    /**
     * 设置书本索引Mapping
     *
     * <description>
     * 只作为测试Mapping相关接口使用
     * 注意：该方法只支持，index中的field未设置的Mapping选项
     * 比如：field的type已经设置type:text,则不允许修改field的type
     * </description>
     *
     * @return
     */
    boolean putIndexMapping();

    /**
     * 书本词向量信息查询
     *
     * @param id document id
     * @return
     */
    TermVectorsResponse bookTermVector(String id);

    /**
     * 赋值索引
     *
     * @param oldIndexName 原索引名称
     * @param newIndexName 新索引名称
     */
    void reBookIndex(String oldIndexName, String newIndexName);

    /**
     * 扫描Mysql数据到Es
     *
     * @param indexName 索引名称
     */
    void scanDbToEs(String indexName);

}
