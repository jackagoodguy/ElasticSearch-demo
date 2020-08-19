package com.elasticsearch.demo.service;


import com.elasticsearch.demo.enums.EsIndexFieldTypeEnum;
import com.elasticsearch.demo.model.indexmapping.BaseMapping;
import com.elasticsearch.demo.model.indexmapping.BookMapping;
import com.elasticsearch.demo.model.indexmapping.MappingFieldProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 书本服务
 *
 * @Author: ShayLau
 * @Date: 2020/8/18 17:08
 */
public interface BookService {

    /**
     * 书本映射关系
     *
     * @return 书本映射关系JSON字符串
     */
    static String indexMapping() {
        BookMapping bookMapping = new BookMapping();
        //文本类型
        MappingFieldProperties textFieldProperties = new MappingFieldProperties(EsIndexFieldTypeEnum.TEXT.getType());
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
}
