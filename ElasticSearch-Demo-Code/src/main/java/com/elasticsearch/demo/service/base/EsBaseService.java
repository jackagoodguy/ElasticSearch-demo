package com.elasticsearch.demo.service.base;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Es基本服务类
 *
 * <description>
 * 处理基本的Es服务
 * </description>
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:31
 */
@Service
@Slf4j
public class EsBaseService {

    @Autowired
    public RestHighLevelClient highLevelClient;


}
