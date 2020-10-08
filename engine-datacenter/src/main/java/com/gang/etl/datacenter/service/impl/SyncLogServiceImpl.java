package com.gang.etl.datacenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.etl.datacenter.entity.SyncBusiness;
import com.gang.etl.datacenter.entity.SyncLog;
import com.gang.etl.datacenter.mapper.SyncLogMapper;
import com.gang.etl.datacenter.service.ISyncLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ant-black
 * @since 2020-09-10
 */
@Service
@Primary
public class SyncLogServiceImpl extends ServiceImpl<SyncLogMapper, SyncLog> implements ISyncLogService {

    /**
     * 通过 Code 和状态查询
     *
     * @return
     */
    public List<SyncLog> findByCodeAndStatus(String code, String status) {
        QueryWrapper<SyncLog> wrapper = new QueryWrapper();
        wrapper.eq("log_channel", code).or().eq("log_status", status);
        return list(wrapper);
    }
}
