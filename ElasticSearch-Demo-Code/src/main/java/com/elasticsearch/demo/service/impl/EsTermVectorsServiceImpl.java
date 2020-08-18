package com.elasticsearch.demo.service.impl;

import com.elasticsearch.demo.service.EsBaseService;
import com.elasticsearch.demo.service.EsTermVectorsService;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;

/**
 * @Author: ShayLau
 * @Date: 2020/8/18 13:53
 */
public class EsTermVectorsServiceImpl extends EsBaseService implements EsTermVectorsService {


    @Override
    public TermVectorsResponse getTermVector(TermVectorsRequest termVectorsRequest) {
        return clientGetTermVector(termVectorsRequest);
    }
}
