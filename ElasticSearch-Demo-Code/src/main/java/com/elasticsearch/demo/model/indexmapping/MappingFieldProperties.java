package com.elasticsearch.demo.model.indexmapping;

import com.elasticsearch.demo.enums.EsIndexFieldTypeEnum;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 映射字段属性
 *
 * @Author: ShayLau
 * @Date: 2020/8/18 18:15
 */
@Data
@AllArgsConstructor
public class MappingFieldProperties implements Serializable {

    @SerializedName("type")
    private String type;

    @SerializedName("index")
    private boolean index;

}
