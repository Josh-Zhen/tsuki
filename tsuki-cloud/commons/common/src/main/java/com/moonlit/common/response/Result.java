package com.moonlit.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应結果集
 *
 * @author Joshua
 * @version 1.0
 * @date 27/10/2022 21:27
 * @email by.Moonlit@hotmail.com
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -7121975455058733108L;

    /**
     * 成功
     */
    public static final Integer CODE_200 = 200;

    /**
     * 失败
     */
    public static final Integer CODE_500 = 500;

    /**
     * 成功标志
     */
    private Boolean success;

    /**
     * 消息
     */
    private String message;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 时间戳
     */
    private Long timestamp = System.currentTimeMillis();

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 成功
     *
     * @param <T> 无入参
     * @return 结果
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(CODE_200);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(CODE_200);
        result.setData(data);
        return result;
    }

    /**
     * 失败
     *
     * @param <T> 无入参
     * @return 结果
     */
    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(CODE_500);
        result.setMessage("fail");
        return result;
    }

    /**
     * 失败
     *
     * @param msg 消息
     * @param <T> 无入参
     * @return 结果
     */
    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(CODE_500);
        result.setMessage(msg);
        return result;
    }

    /**
     * 失败
     *
     * @param code 错误码
     * @param msg  消息
     * @param <T>  无入参
     * @return 结果
     */
    public static <T> Result<T> fail(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

}
