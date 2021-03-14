package com.gang.etl.engine.strategy.operation;

import cn.hutool.core.collection.CollectionUtil;
import com.gang.etl.engine.api.to.EngineConsumerBean;
import com.gang.etl.engine.api.to.EngineProduceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PageStrategy
 * @Description 数据切分策略
 * @Date 2021/2/28 13:05
 * @Created by zengzg
 */
@Service
public class PageStrategy {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 把过大的数据进行切分 , 放置
     *
     * @param produceBean
     * @return
     */
    public List<EngineConsumerBean> splitBean(EngineConsumerBean produceBean) {


        List<EngineConsumerBean> respoonseList = new ArrayList<>();
        if (produceBean.getData() != null && produceBean.getData().size() > getSize()) {
            List<List> splitResult = CollectionUtil.split(produceBean.getData(), getSize());

            splitResult.forEach(itemList -> {
                try {
                    EngineConsumerBean newResponseItem = new EngineConsumerBean();
                    BeanCopier beanCopier = BeanCopier.create(EngineConsumerBean.class, EngineConsumerBean.class, true);
                    beanCopier.copy(produceBean, newResponseItem, new Converter() {
                        @Override
                        public Object convert(Object value, Class target, Object context) {
                            if (value instanceof List) {
                                return null;
                            } else {
                                return value;
                            }
                        }
                    });

                    newResponseItem.setData(itemList);
                    respoonseList.add(newResponseItem);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
                }
            });
        } else {
            respoonseList.add(produceBean);
        }
        return respoonseList;
    }


    /**
     * TODO : 根据 Business 类型获取
     *
     * @return
     */
    public int getSize() {
        return 3;
    }
}
