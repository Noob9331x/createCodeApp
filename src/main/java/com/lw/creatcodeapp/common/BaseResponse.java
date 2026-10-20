package com.lw.creatcodeapp.common;

import com.lw.creatcodeapp.exception.ErrorCode;
import lombok.Data;
import java.io.Serializable;

/**
 * 通用基础响应类，用于封装API返回的统一数据格式
 * 使用泛型T可以支持不同类型的数据返回
 * 实现Serializable接口以支持序列化
 *
 * @param <T> 泛型类型，表示返回的数据类型
 */
@Data
public class BaseResponse<T> implements Serializable {

    /**
     * 响应状态码，用于标识请求的处理结果状态
     */
    private int code;

    private T data;
    /**
     * 响应数据，泛型类型，可以包含任意类型的数据
     */

    private String message;
    /**
     * 响应消息，用于返回处理结果的描述信息
     */

    public BaseResponse(int code, T data, String message) {
        /**
         * 全参数构造方法，初始化所有字段
         *
         * @param code    响应状态码
         * @param data    响应数据
         * @param message 响应消息
         */
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    /**
     * 数据和状态码构造方法，消息默认为空字符串
     *
     * @param code 响应状态码
     * @param data 响应数据
     */
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    /**
     * 错误码构造方法，用于快速创建错误响应
     *
     * @param ErrorCode 错误码枚举，包含状态码和错误信息
     */
    }
}
