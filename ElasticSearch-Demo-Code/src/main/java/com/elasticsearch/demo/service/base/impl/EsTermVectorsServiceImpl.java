package com.elasticsearch.demo.service.base.impl;

import com.elasticsearch.demo.service.base.EsBaseService;
import com.elasticsearch.demo.service.base.EsTermVectorsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: ShayLau
 * @Date: 2020/8/18 13:53
 */
@Service
@Slf4j
public class EsTermVectorsServiceImpl extends EsBaseService implements EsTermVectorsService {

    /**
     * 获取词向量信息
     *
     * @param termVectorsRequest
     * @return
     */
    @Override
    public TermVectorsResponse getTermVector(TermVectorsRequest termVectorsRequest) {
        try {
            highLevelClient.termvectors(termVectorsRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("get termVectors Exception：" + e.getMessage());
        }
        return null;
    }

}
