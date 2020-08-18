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
     * @return
     */
    static String indexMapping() {
        BookMapping bookMapping = new BookMapping();
        bookMapping.setAuthor(new MappingFieldProperties(EsIndexFieldTypeEnum.TEXT.getType(), false));
        bookMapping.setChineseTitle(new MappingFieldProperties(EsIndexFieldTypeEnum.TEXT.getType(), false));
        bookMapping.setDescription(new MappingFieldProperties(EsIndexFieldTypeEnum.TEXT.getType(), false));
        bookMapping.setIsbn(new MappingFieldProperties(EsIndexFieldTypeEnum.TEXT.getType(), false));
        bookMapping.setSubTitle(new MappingFieldProperties(EsIndexFieldTypeEnum.TEXT.getType(), false));
        bookMapping.setTitle(new MappingFieldProperties(EsIndexFieldTypeEnum.TEXT.getType(), false));
        BaseMapping baseMapping = new BaseMapping(bookMapping);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(baseMapping);
    }
}
