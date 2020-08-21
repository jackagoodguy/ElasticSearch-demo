package com.elasticsearch.demo.service.base.impl;

import com.elasticsearch.demo.listener.GetResponseListener;
import com.elasticsearch.demo.service.base.EsGetService;
import com.elasticsearch.demo.service.base.EsBaseService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * GetServiceImpl
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:42
 */
@Service
@Slf4j
public class EsGetServiceImpl extends EsBaseService implements EsGetService {

    @Autowired
    private RestHighLevelClient highLevelClient;


    @Override
    public GetResponse getById(String indexName, String id) {
        try {
            GetRequest getRequest = new GetRequest(indexName, id);
            return highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Es get IOException Error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public GetResponse getPartField(String indexName, String id, String[] includes, String[] excludes) {
        try {
            GetRequest getRequest = new GetRequest(indexName, id);
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
            getRequest.fetchSourceContext(fetchSourceContext);
            return highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Es get IOException Error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public void getByListener(String indexName, String id, GetResponseListener listener) {
        GetRequest getRequest = new GetRequest(indexName, id);
        highLevelClient.getAsync(getRequest, RequestOptions.DEFAULT, listener);
    }
}
