package com.gang.etl.datacenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.datacenter.mapper.SyncSettingMapper;
import com.gang.etl.datacenter.service.ISyncSettingService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ant-black
 * @since 2020-01-31
 */
@Service
public class SyncSettingServiceImpl extends ServiceImpl<SyncSettingMapper, SyncSetting> implements ISyncSettingService {

}
