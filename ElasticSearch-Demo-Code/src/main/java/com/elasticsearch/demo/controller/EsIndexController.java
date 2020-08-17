package com.elasticsearch.demo.controller;

import com.elasticsearch.demo.common.RestResultBody;
import com.elasticsearch.demo.service.EsIndexService;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: ShayLau
 * @Date: 2020/8/17 14:29
 */
@RequestMapping("/es")
@RestController
public class EsIndexController {

    @Autowired
    private EsIndexService indexService;


    /**
     * 创建索引
     *
     * @param indexName
     * @return
     */
    @GetMapping("/create/index")
    public RestResultBody createIndex(@RequestParam String indexName) {

        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        ///索引的配置信息
        //createIndexRequest.settings(Settings.builder().put("",""));
        //createIndexRequest.settings(new HashMap<>());

        CreateIndexResponse createIndexResponse = indexService.createIndex(createIndexRequest);
        return RestResultBody.success(createIndexResponse);
    }

    /**
     * 获取索引
     *
     * @param indices
     * @return
     */
    @GetMapping("/get/index")
    public RestResultBody getIndex(@RequestParam String[] indices) {
        GetIndexRequest getIndexRequest = new GetIndexRequest(indices);
        GetIndexResponse getIndexResponse = indexService.getIndex(getIndexRequest);
        return RestResultBody.success(getIndexResponse);
    }

}
