package com.elasticsearch.demo.service.base;

import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;

/**
 * 词向量信息
 * 所谓词向量，就是查看索引文档中某个字段出现的频率及位置信息、偏向量信息
 *
 * @Author: ShayLau
 * @Date: 2020/8/18 13:50
 */
public interface EsTermVectorsService {

    /**
     * 获取词向量信息
     *
     * @param termVectorsRequest
     * @return
     */
    TermVectorsResponse getTermVector(TermVectorsRequest termVectorsRequest);

}
