package com.elasticsearch.demo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ShayLau
 * @Date: 2020/8/18 14:17
 */
@TableName("book")
@Data
public class Book implements Serializable {


    @TableId
    private String id;
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
