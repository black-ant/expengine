package com.gang.etl.engine.logic;

import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.template.ConsumerTemplate;
import com.gang.etl.engine.template.ProduceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname SimpleEngineLogic
 * @Description TODO
 * @Date 2020/6/20 19:08
 * @Created by zengzg
 */
@Component
public class SimpleEngineLogic extends BaseSyncLogic {

    @Autowired
    private ProduceTemplate produceTemplate;

    @Autowired
    private ConsumerTemplate consumerTemplate;


    /**
     * excute one sync by type
     *
     * @param engineBaseBean
     */
    public EngineBaseBean excuteByType(EngineBaseBean engineBaseBean) {
        beforeSync(engineBaseBean);

        if (engineBaseBean.getTypePart().equals(engineBaseBean.OP_PRODUCE)) {
//            engineBaseBean = produceTemplate.excute(engineBaseBean);
        } else {
//            engineBaseBean = consumerTemplate.excute(engineBaseBean);
        }

        return engineBaseBean;

    }
}
