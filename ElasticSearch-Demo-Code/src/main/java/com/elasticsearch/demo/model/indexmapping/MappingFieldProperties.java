package com.elasticsearch.demo.model.indexmapping;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 索引中Mapping的属性配置列表(不建议使用构造该对象方式设置字段配置信息)
 *
 * <description>
 * 官网Mapping介绍：https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping.html
 * Mapping field type:https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-types.html
 * Mapping field params:https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-params.html
 * </description>
 *
 * @Author: ShayLau
 * @Date: 2020/8/18 18:15
 */
@Data
@AllArgsConstructor
public class MappingFieldProperties implements Serializable {

    /**
     * 字段类型
     *
     * <description>
     * 官方字段类型列表 https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-types.html
     * 参考字段类型定义枚举 {@link com.elasticsearch.demo.enums.EsIndexFieldTypeEnum}
     * </description>
     */
    @SerializedName("type")
    private String type;

    /**
     * 字段是否索引
     *
     * <description>
     * The index option controls whether field values are indexed.
     * It accepts true or false and defaults to true. Fields that are not indexed are not queryable.
     * 没有索引的字段是不可查询的,默认设置为true
     * </description>
     */
    @SerializedName("index")
    private boolean index;

    public MappingFieldProperties(String type) {
        this.type = type;
    }
}
