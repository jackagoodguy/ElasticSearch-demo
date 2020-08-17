package com.elasticsearch.demo.enums;

/**
 * 请求结果编码枚举
 *
 * @Author: ShayLau
 * @Date: 2020/8/17 10:17
 */
public enum ResultCodeEnum {

    SUCCESS(200),
    FAIlURE(500);

    private Integer value;

    ResultCodeEnum(Integer value) {
        this.value = value;
    }

    public Integer code() {
        return value;
    }
}
