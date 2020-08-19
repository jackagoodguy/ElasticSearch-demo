package com.elasticsearch.demo.service.base;

import com.elasticsearch.demo.listener.GetResponseListener;
import org.elasticsearch.action.get.GetResponse;


/**
 * @Author: ShayLau
 * @Date: 2020/8/17 10:33
 */
public interface EsGetService {

    /**
     * 获取Es内容根据id
     *
     * @param indexName 索引名称
     * @param id        id
     * @return
     */
    GetResponse getById(String indexName, String id);

    /**
     * 获取Es内容
     *
     * @param indexName 索引名称
     * @param id        id
     * @param includes  需要获取的字段
     * @param excludes  排除获取的字段
     * @return
     */
    GetResponse getPartField(String indexName, String id, String[] includes, String[] excludes);

    /**
     * 获取Es内容
     *
     * @param indexName 索引名称
     * @param id        id
     * @param listener  监听器
     */
    void getByListener(String indexName, String id, GetResponseListener listener);


}
