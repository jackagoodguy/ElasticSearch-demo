package com.elasticsearch.demo.listener;

import lombok.Data;
import org.elasticsearch.action.ActionListener;

/**
 * ES http request响应监听器
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 9:56
 */
@Data
public abstract class BaseResponseListener<R> implements ActionListener<R> {
    /**
     * 响应数据
     */
    private R responseData;

}
