package com.elasticsearch.demo.model;

import lombok.Data;

import java.util.List;

/**
 * 书本scroll查询BO
 *
 * @Author: ShayLau
 * @Date: 2020/8/26 17:54
 */
@Data
public class BookScrollSearchBO {

    private String scrollId;

    private List<Book> bookList;
}
