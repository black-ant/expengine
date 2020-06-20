package com.gang.etl.engine.api.common;

import com.gang.etl.engine.api.bean.EngineBaseBean;

/**
 * @Classname IEngineService
 * @Description TODO
 * @Date 2020/6/20 20:43
 * @Created by zengzg
 */
public interface IEngineService<C extends EngineBaseBean> {
    C execute(C consumerBean);
}
