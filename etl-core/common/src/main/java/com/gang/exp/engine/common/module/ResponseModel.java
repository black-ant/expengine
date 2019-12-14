package com.gang.exp.engine.common.module;

/**
 * 统一处理
 *
 * @param <E>
 */
public class ResponseModel<E> {

    private String code;

    private E data;

    public static class Builder<E> {

        protected ResponseModel instance;

        protected ResponseModel<E> getInstance() {
            return instance == null ? new ResponseModel<E>() : instance;
        }

        public Builder<E> data(E data) {
            getInstance().setData(data);
            return this;
        }

        public Builder<E> code(String value) {
            getInstance().setCode(value);
            return this;
        }

        public ResponseModel<E> build() {
            return getInstance();
        }


    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
