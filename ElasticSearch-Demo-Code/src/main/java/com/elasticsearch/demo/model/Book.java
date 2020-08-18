package com.elasticsearch.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ShayLau
 * @Date: 2020/8/18 14:17
 */
@Data
public class Book implements Serializable {


    /**
     * ISBN
     */
    private String isbn;

    /**
     * 中文标题
     */
    private String chineseTitle;

    /**
     * 标题
     */
    private String title;

    /**
     * 子标题
     */
    private String subTitle;

    /**
     * 作者
     */
    private String author;

    /**
     * 描述
     */
    private String description;


}
