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
public class RestResultBody {

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

    public RestResultBody(Integer code) {
        this.code = code;
    }

    public RestResultBody(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public RestResultBody(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public static RestResultBody success() {
        return new RestResultBody(ResultCodeEnum.SUCCESS.code());
    }

    public static RestResultBody success(Object data) {
        return new RestResultBody(ResultCodeEnum.SUCCESS.code(), data);
    }


    public RestResultBody failure() {
        return new RestResultBody(ResultCodeEnum.FAIlURE.code());
    }


    public RestResultBody failure(String message) {
        return new RestResultBody(ResultCodeEnum.FAIlURE.code(), message);
    }


}
