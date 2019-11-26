package com.xmd.firstBoot.response;



import java.io.Serializable;

/**
 * @Author: xx
 * @Description:
 * @Date: Created in 18:27 2019/11/25
 */
public class CommonResult<E> implements Serializable {

    //状态码
    private Integer code;
    //状态：true 成功
    private Boolean state;
    //执行结果消息提示信息
    private String message;

    private E value;

    public CommonResult(String message) {
        this(true, message, null);
    }

    public CommonResult(boolean success, String message) {
        this(success, message, null);
    }

    public CommonResult(boolean success, String message, E value) {
        this(success, message, value, (Integer)null);
    }

    public CommonResult(boolean success, String message, E value, Integer code) {
        this.state = true;
        this.state = success;
        this.message = message;
        this.value = value;
        this.code = code;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
