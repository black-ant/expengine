package com.gang.expengine.core.controller.common;

import com.gang.expengine.core.common.model.ResponseModel;
import com.gang.expengine.core.controller.AbstarctController;
import com.gang.expengine.core.exception.ExchangeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Classname ExceptionAdvice
 * @Description TODO
 * @Date 2019/10/31 23:03
 * @Created by ant-black 1016930479@qq.com
 */
@ControllerAdvice
public class ExceptionAdvice extends AbstarctController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Error advice
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ExchangeException.class})
    public ResponseModel handleMsgServerException(ExchangeException e) {
        logger.error("----> Error Msg is :{}", e.getMessage());
        return createErrorResponse(e.getErrorMsg(), e.getErrorCode());
    }
}
