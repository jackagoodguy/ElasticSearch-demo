package com.elasticsearch.demo.controller;

import com.elasticsearch.demo.common.Constant;
import com.elasticsearch.demo.common.RestResultBody;
import com.elasticsearch.demo.service.EsGetService;
import org.elasticsearch.action.get.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 书本查询控制器
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:12
 */
@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private EsGetService getService;





    @GetMapping("")
    public RestResultBody list(@RequestParam String id) {
        GetResponse getResponse = getService.getById(Constant.DEFAULT_ES_INDEX_NAME, id);
        return RestResultBody.success(getResponse);
    }


    @GetMapping("/page")
    public RestResultBody page(Integer size, Integer page) {

        return RestResultBody.success();
    }


}
