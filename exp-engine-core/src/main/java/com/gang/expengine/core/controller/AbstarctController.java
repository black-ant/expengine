package com.gang.expengine.core.controller;

import com.gang.expengine.core.common.model.ResponseModel;

/**
 * @Classname AbstarctController
 * @Description TODO
 * @Date 2019/10/29 22:50
 * @Created by ant-black 1016930479@qq.com
 */
public class AbstarctController<E> {

    protected <TO> ResponseModel<TO> createResponse(final TO entity) {
        return new ResponseModel.Builder<TO>().data(entity).code("1").build();
    }

    protected <TO> ResponseModel<TO> createErrorResponse(final TO entity, final String errorCode) {
        return new ResponseModel.Builder<TO>().data(entity).code(errorCode).build();
    }
}
