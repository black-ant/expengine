package com.gang.etl.datacenter.service.impl;

import com.gang.etl.datacenter.entity.SyncLog;
import com.gang.etl.datacenter.mapper.SyncLogMapper;
import com.gang.etl.datacenter.service.ISyncLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ant-black
 * @since 2020-02-02
 */
@Service
public class SyncLogServiceImpl extends ServiceImpl<SyncLogMapper, SyncLog> implements ISyncLogService {

}
