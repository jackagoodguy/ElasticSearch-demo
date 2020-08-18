package com.elasticsearch.demo.controller;

import com.elasticsearch.demo.common.Constant;
import com.elasticsearch.demo.common.RestResultBody;
import com.elasticsearch.demo.service.BookService;
import com.elasticsearch.demo.service.EsIndexService;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 书本表ElasticSearch 索引操作
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 14:29
 */
@RequestMapping("/es")
@RestController
public class EsBookIndexController {

    @Autowired
    private EsIndexService indexService;

    @Autowired
    private BookService bookService;


    /**
     * 创建书本索引
     *
     * @return
     */
    @PostMapping("/create/index/book")
    public RestResultBody createBookIndex() {
        GetIndexRequest getIndexRequest = new GetIndexRequest(Constant.DEFAULT_ES_INDEX_NAME);
        boolean existIndex = indexService.existIndex(getIndexRequest);
        if (!existIndex) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(Constant.DEFAULT_ES_INDEX_NAME);
            createIndexRequest.mapping(BookService.indexMapping(), XContentType.JSON);
            CreateIndexResponse createIndexResponse = indexService.createIndex(createIndexRequest);
            boolean acknowledged = createIndexResponse.isAcknowledged();
            return RestResultBody.success(acknowledged);
        }
        return RestResultBody.success();
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
