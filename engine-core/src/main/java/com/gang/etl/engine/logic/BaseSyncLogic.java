package com.gang.etl.engine.logic;

import com.gang.common.lib.exception.CommonException;
import com.gang.common.lib.utils.AntAnnotationUtils;
import com.gang.common.lib.utils.ReflectionUtils;
import com.gang.etl.datacenter.dao.SyncTypeDAO;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.api.to.EngineBaseSetting;
import com.gang.etl.engine.api.bean.ISyncBaseTO;
import com.gang.etl.engine.api.common.IEngineService;
import com.gang.etl.engine.api.exception.SyncErrorEnum;
import com.gang.etl.engine.api.exception.SyncException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname BaseSyncController
 * @Description TODO
 * @Date 2020/6/27 21:52
 * @Created by zengzg
 */
public class BaseSyncLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncTypeDAO syncTypeDAO;

    @Autowired
    private ReflectionUtils reflectionUtils;

    /**
     * Sync 同步前处理
     *
     * @param engineBaseBean
     */
    public void beforeSync(EngineBaseBean engineBaseBean) {

        checkBeforeEngineBaseBean(engineBaseBean);

        rebuildEngineBaseBean(engineBaseBean);

        checkAfterEngineBaseBean(engineBaseBean);
    }

    /**
     * EngineBaseBean 二次构建
     *
     * @param engineBaseBean
     */
    public void rebuildEngineBaseBean(EngineBaseBean engineBaseBean) {

        // Search SyncType
        SyncType syncType = syncTypeDAO.getByTypeCode(engineBaseBean.getSyncType());
        if (null == syncType) {
            throw new SyncException(SyncErrorEnum.SYNC_SYNCTYPE_ERROR.getErrorCode(), "Sync Type Code Errro");
        }
        engineBaseBean.setTypeOperation(syncType.getTypeOperation());
        engineBaseBean.setTypePart(syncType.getTypePart());
        engineBaseBean.setServiceName(syncType.getTypeClass());

        // Build IEngineService
        IEngineService engineService = reflectionUtils.springClassLoad(syncType.getTypeClass());
        if (null == engineService) {
            throw new SyncException(SyncErrorEnum.SYNC_SYNCSERVICE_ERROR.getErrorCode(), "Sync Service Errro");
        }
        engineBaseBean.setEngineService(engineService);

        // Build EngineBaseSetting And Check
        if (StringUtils.isNotEmpty(syncType.getSettingClass())) {
            EngineBaseSetting setting = reflectionUtils.springClassLoad(syncType.getSettingClass());

            try {
                AntAnnotationUtils.checkFieldAnno(setting);
            } catch (CommonException exception) {
                throw new SyncException(SyncErrorEnum.SYNC_SYNCSERVICE_ERROR.getErrorCode(), exception.getMessage());
            }
        }

        // Build EngineBaseSetting And Check
        if (StringUtils.isNotEmpty(syncType.getBeanClass())) {
            ISyncBaseTO baseTO = reflectionUtils.springClassLoad(syncType.getBeanClass());

            try {
                AntAnnotationUtils.checkFieldAnno(baseTO);
            } catch (CommonException exception) {
                throw new SyncException(SyncErrorEnum.SYNC_SYNCSERVICE_ERROR.getErrorCode(), exception.getMessage());
            }
        }

    }


    /**
     * EngineBaseBean 前置校验
     *
     * @param engineBaseBean
     */
    public void checkBeforeEngineBaseBean(EngineBaseBean engineBaseBean) {
        if (StringUtils.isEmpty(engineBaseBean.getSyncType())) {
            throw new SyncException(SyncErrorEnum.SYNC_SYNCTYPE_ERROR.getErrorCode(), "No Sync Type");
        } else if (null == engineBaseBean.getData()) {
            throw new SyncException(SyncErrorEnum.SYNC_SYNCDATE_ERROR.getErrorCode(), "No Sync Data");
        }
    }


    /**
     * EngineBaseBean 后置校验
     *
     * @param engineBaseBean
     */
    public void checkAfterEngineBaseBean(EngineBaseBean engineBaseBean) {
        logger.info("------> this is over <-------");
    }


}
