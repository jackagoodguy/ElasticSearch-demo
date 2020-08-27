package com.elasticsearch.demo.service.base.impl;

import com.elasticsearch.demo.model.Book;
import com.elasticsearch.demo.model.indexmapping.BookMapping;
import com.elasticsearch.demo.service.base.EsBaseService;
import com.elasticsearch.demo.service.base.EsScrollService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.client.RequestOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author: ShayLau
 * @Date: 2020/8/27 14:58
 */
@Service
@Slf4j
public class EsScrollServiceImpl extends EsBaseService<BookMapping, Book> implements EsScrollService {


    @Override
    public boolean clearScroll(List<String> scrollId) {
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.scrollIds(scrollId);
        try {
            ClearScrollResponse clearScrollResponse = highLevelClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
            return clearScrollResponse.isSucceeded();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("es clear Scroll id exception:" + e.getMessage());
            return false;
        }
    }
}
