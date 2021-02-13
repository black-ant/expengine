package com.gang.etl.git.logic;

import com.gang.etl.engine.api.to.EngineProduceBean;
import com.gang.etl.engine.api.common.IProduceService;
import com.gang.etl.engine.api.service.BaseProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname GitProduceService
 * @Description TODO
 * @Date 2020/7/3 12:45
 * @Created by zengzg
 */
@Component
public class GitProduceService extends BaseProduceService implements IProduceService {

    @Autowired
    private GitOperation gitOperation;

    @Override
    public EngineProduceBean execute(EngineProduceBean consumerBean) {

        String config = consumerBean.getSetting();

        //        gitOperation.pullGit();

        return null;
    }
}
