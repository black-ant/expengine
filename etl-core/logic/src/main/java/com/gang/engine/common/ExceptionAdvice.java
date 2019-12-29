package com.gang.engine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @Classname ExceptionAdvice
 * @Description TODO
 * @Date 2019/10/31 23:03
 * @Created by ant-black 1016930479@qq.com
 */

@ControllerAdvice
public class ExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Error advice
     *
     * @param e
     * @return
     */
    //    @ExceptionHandler({ExchangeException.class})
    //    public ResponseModel handleMsgServerException(ExchangeException e) {
    //        logger.error("----> Error Msg is :{}", e.getMessage());
    //        return createErrorResponse(e.getErrorMsg(), e.getErrorCode());
    //    }

}
