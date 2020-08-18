package com.elasticsearch.demo.model.indexmapping;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * Es Index索引Mapping
 *
 * @Author: ShayLau
 * @Date: 2020/8/18 17:15
 */
@Data
public class BookMapping implements Serializable {


    /**
     * ISBN
     */
    @SerializedName("isbn")
    private MappingFieldProperties isbn;

    /**
     * 中文标题
     */
    @SerializedName("chineseTitle")
    private MappingFieldProperties chineseTitle;

    /**
     * 标题
     */
    @SerializedName("title")
    private MappingFieldProperties title;

    /**
     * 子标题
     */
    @SerializedName("subTitle")
    private MappingFieldProperties subTitle;

    /**
     * 作者
     */
    @SerializedName("author")
    private MappingFieldProperties author;

    /**
     * 描述
     */
    @SerializedName("description")
    private MappingFieldProperties description;

}
