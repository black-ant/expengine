package com.gang.etl.server.logic;

import com.gang.common.lib.utils.ReflectionUtils;
import com.gang.etl.datacenter.dao.SyncFiledInfoDAO;
import com.gang.etl.datacenter.dao.SyncTypeDAO;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.entity.SyncType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname SyncFieldInfoLogic
 * @Description TODO
 * @Date 2019/12/30 17:01
 * @Created by zengzg
 */
@Component
public class SyncFieldInfoLogic {

    @Autowired
    private SyncFiledInfoDAO syncFiledInfoDAO;

    @Autowired
    private SyncTypeDAO syncTypeDAO;

    public SyncFieldInfo insert(SyncFieldInfo syncFieldInfo) {
        return syncFiledInfoDAO.insert(syncFieldInfo);
    }

    public SyncFieldInfo selectByPrimaryKey(Integer key) {
        return syncFiledInfoDAO.selectByPrimaryKey(key);
    }

    public Map<String, String> getTosMap(String syncType) {
        Map<String, String> map = new HashMap<>();
        syncTypeDAO.getByKey()
        return map;
    }

    public List<String> getFieldList(SyncType syncType) {

        List<String> classList = new ArrayList<>();
        String packageName = syncType.getTypeClass();

        classList = ReflectionUtils.scanClazzName(packageName, Boolean.FALSE);

        return classList;
    }


}
