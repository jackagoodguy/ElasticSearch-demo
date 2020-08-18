package com.elasticsearch.demo.enums;

/**
 * Es索引字段类型枚举
 *
 * @Author: ShayLau
 * @Date: 2020/8/18 17:01
 */
public enum EsIndexFieldTypeEnum {
    /**
     * 文本类型
     */
    TEXT("text"),
    /**
     * 关键词类型
     */
    KEYWORD("keyword"),
    /**
     * 事件类型
     */
    DATE("date"),
    /**
     * 数值
     */
    LONG("long"),
    /**
     * 浮点数
     */
    DOUBLE("double"),
    /**
     * 布尔类型
     */
    BOOLEAN("boolean"),
    /**
     * 地理位置
     */
    GEO_POINT("geo_point"),
    /**
     * 地址位置
     */
    GEO_SHAPE("geo_shape");


    EsIndexFieldTypeEnum(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }
}
