package com.elasticsearch.demo.model.indexmapping;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ShayLau
 * @Date: 2020/8/18 18:07
 */
@Data
@AllArgsConstructor
public class BaseMapping implements Serializable {

    /**
     * 属性
     */
    @SerializedName("properties")
    private Object properties;
}
