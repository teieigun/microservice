package com.microservice.edu.form.comments;

import lombok.Data;
import java.io.Serializable;


/**
 * 返回结果的封装类
 * @param <T>
 */
@Data
public class ResultDT<T> implements Serializable {

    /**
     * 返回码
     */
    public Integer code;

    /**
     * 返回信息
     */
    public String msg;

    /**
     * 返回的数据
     */
    public T data;

    /**
     * 附加数据
     */
    public T addData;

    public ResultDT() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getAddData() {
        return addData;
    }

    public void setAddData(T addData) {
        this.addData = addData;
    }
}

