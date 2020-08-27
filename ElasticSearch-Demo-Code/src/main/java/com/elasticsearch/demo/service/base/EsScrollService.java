package com.elasticsearch.demo.service.base;

import java.util.List;

/**
 * @Author: ShayLau
 * @Date: 2020/8/27 15:19
 */
public interface EsScrollService {

    /**
     * 清除Scroll
     *
     * @param scrollId scroll Id
     * @return
     */
    boolean clearScroll(List<String> scrollId);
}
