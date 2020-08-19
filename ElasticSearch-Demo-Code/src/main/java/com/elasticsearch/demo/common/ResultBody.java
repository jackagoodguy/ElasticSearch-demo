package com.elasticsearch.demo.common;

import com.elasticsearch.demo.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Rest 结果内容封装
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBody {

    /**
     * Rest请求结果响应码
     */
    private Integer code;
    /**
     * 请求结果
     */
    private Object data;

    /**
     * 请求结果
     */
    private String message;

    public ResultBody(Integer code) {
        this.code = code;
    }

    public ResultBody(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultBody(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public static ResultBody success() {
        return new ResultBody(ResultCodeEnum.SUCCESS.code());
    }

    public static ResultBody success(Object data) {
        return new ResultBody(ResultCodeEnum.SUCCESS.code(), data);
    }


    public ResultBody failure() {
        return new ResultBody(ResultCodeEnum.FAIlURE.code());
    }


    public ResultBody failure(String message) {
        return new ResultBody(ResultCodeEnum.FAIlURE.code(), message);
    }


}
