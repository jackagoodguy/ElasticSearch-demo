package com.elasticsearch.demo.service.impl;

import com.elasticsearch.demo.common.Constant;
import com.elasticsearch.demo.listener.GetResponseListener;
import com.elasticsearch.demo.service.EsGetService;
import com.elasticsearch.demo.service.EsHighLevelRestService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.get.GetResponse;
import org.springframework.stereotype.Service;


/**
 * GetServiceImpl
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:42
 */
@Service
public class EsGetServiceImpl extends EsHighLevelRestService implements EsGetService {

    /**
     * 根据id获取内容
     *
     * @param indexName 索引名称
     * @param id        id
     * @return
     */
    @Override
    public GetResponse getById(String indexName, String id) {
        if (StringUtils.isBlank(indexName)) {
            indexName = Constant.DEFAULT_ES_INDEX_NAME;
        }
        return clientGetById(indexName, id);
    }

    /**
     * 获取部分字段
     *
     * @param indexName 索引名称
     * @param id        id
     * @param includes  需要获取的字段
     * @param excludes  排除获取的字段
     * @return
     */
    @Override
    public GetResponse getPartField(String indexName, String id, String[] includes, String[] excludes) {
        if (StringUtils.isBlank(indexName)) {
            indexName = Constant.DEFAULT_ES_INDEX_NAME;
        }
        return clientGetPartFields(indexName, id, includes, excludes);
    }


    /**
     * 获取内容根据监听器
     *
     * @param indexName 索引名称
     * @param id        id
     * @param listener  监听器
     */
    @Override
    public void getByListener(String indexName, String id, GetResponseListener listener) {
        if (StringUtils.isBlank(indexName)) {
            indexName = Constant.DEFAULT_ES_INDEX_NAME;
        }
        clientGetAsync(indexName, id, listener);
    }


}
