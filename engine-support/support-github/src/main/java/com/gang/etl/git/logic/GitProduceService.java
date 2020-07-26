package com.gang.etl.git.logic;

import com.gang.etl.engine.api.bean.EngineProduceBean;
import com.gang.etl.engine.api.common.IProduceService;
import com.gang.etl.engine.api.service.BaseProduceService;
import org.springframework.stereotype.Component;

/**
 * @Classname GitProduceService
 * @Description TODO
 * @Date 2020/7/3 12:45
 * @Created by zengzg
 */
@Component
public class GitProduceService extends BaseProduceService implements IProduceService {

    @Override
    public EngineProduceBean execute(EngineProduceBean consumerBean) {

        String config = consumerBean.getConfig();


        return null;
    }
}
