package com.gang.etl.engine.strategy;

import com.gang.etl.engine.api.to.EngineBaseBean;

/**
 * @Classname ISyncStrategy
 * @Description 同步策略 , 同步的 BaseBean 生成方式
 * @Date 2020/10/8 16:33
 * @Created by zengzg
 */
public interface ISyncStrategy {

    EngineBaseBean getBaseBean(String code, EngineBaseBean baseBean);

    void setBaseBean(EngineBaseBean baseBean);
}
