package com.gang.etl.engine.template;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.datacenter.entity.SyncBusiness;
import com.gang.etl.datacenter.entity.SyncBusinessItem;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.service.ISyncBusinessService;
import com.gang.etl.datacenter.service.impl.SyncBusinessItemServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncFieldInfoServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncSettingServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
import com.gang.etl.engine.api.dto.SyncLogTO;
import com.gang.etl.engine.api.request.SyncCustomRequest;
import com.gang.etl.engine.api.to.EngineConsumerBean;
import com.gang.etl.engine.conversion.DictionaryConversion;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname RestFlowTemplate
 * @Description TODO
 * @Date 2021/3/14 18:31
 * @Created by zengzg
 */
@Component
public class RestFlowTemplate {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerTemplate consumerTemplate;

    @Autowired
    private SyncSettingServiceImpl syncSettingService;

    @Autowired
    private SyncFieldInfoServiceImpl syncFieldInfoService;

    @Autowired
    private SyncBusinessItemServiceImpl businessItemService;

    @Autowired
    private SyncErrorTemplate syncErrorTemplate;

    @Autowired
    private ISyncBusinessService syncBusinessService;

    @Autowired
    private DictionaryConversion dictionaryConversion;

    @Autowired
    private SyncTypeServiceImpl syncTypeService;


    public List<SyncLogTO> doPull() {
        return null;
    }

    public List<SyncLogTO> doPush(SyncCustomRequest request) {

        // Step 1 : 获取 SyncBusiness
        SyncBusinessItem business = StringUtils.isNotBlank(request.getBusinessCode()) ? businessItemService.findByItemCode(request.getBusinessCode()) : null;


        List<SyncLogTO> responseList = null;
        // SyncBusiness Code 存在和不存在分开处理
        if (business != null) {
            responseList = doPushWorkBySyncBusiness(business, request);
        } else {
            if (request.getSyncBeanCode() == null || request.getFieldInfoCode() == null || request.getSyncSettingCode() == null) {
                logger.info("------> doPush need necessary param<-------");
            } else {
                responseList = doPushWorkByOther(request);
            }
        }

        return responseList;

    }

    /**
     * SyncBusiness 存在的处理
     *
     * @param businessItem
     */
    public List<SyncLogTO> doPushWorkBySyncBusiness(SyncBusinessItem businessItem, SyncCustomRequest request) {

        SyncBusiness syncBusiness = syncBusinessService.getById(businessItem.getId());

        // ConsumerSetting
        SyncSetting syncSettingConsumer = syncSettingService.getById(syncBusiness.getSyncConsumerSetting());

        // Add SyncFieldInfo
        SyncFieldInfo fieldInfo = syncFieldInfoService.getById(businessItem.getSyncFieldId());

        List<JSONObject> exchangeData = dictionaryConversion.conversionField(request.getData(), fieldInfo);

        EngineConsumerBean bean = new EngineConsumerBean();
        bean.setConsumerService(syncBusiness.getSyncConsumer());
        bean.setServiceName(syncBusiness.getSyncConsumer());
        bean.setSetting(syncSettingConsumer.getSettingBody());
        bean.setData(exchangeData);

        consumerTemplate.excute(bean, null);

        return null;

    }

    /**
     * @param request
     * @return
     */
    public List<SyncLogTO> doPushWorkByOther(SyncCustomRequest request) {

        // ConsumerSetting
        SyncSetting syncSettingConsumer = syncSettingService.getById(request.getSyncSettingCode());

        // Add SyncFieldInfo
        SyncFieldInfo fieldInfo = syncFieldInfoService.getById(request.getFieldInfoCode());

        SyncType syncType = syncTypeService.getById(request.getSyncBeanCode());

        // 数据转换
        List<JSONObject> exchangeData = dictionaryConversion.conversionField(request.getData(), fieldInfo);

        EngineConsumerBean bean = new EngineConsumerBean();
        bean.setConsumerService(syncType.getTypeClass());
        bean.setServiceName(syncType.getTypeClass());
        bean.setSetting(syncSettingConsumer.getSettingBody());
        bean.setData(exchangeData);

        consumerTemplate.excute(bean, null);

        return null;

    }

}
