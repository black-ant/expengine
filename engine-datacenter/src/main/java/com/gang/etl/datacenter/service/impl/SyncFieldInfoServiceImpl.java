package com.gang.etl.datacenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.mapper.SyncFieldInfoMapper;
import com.gang.etl.datacenter.service.ISyncFieldInfoService;
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
public class SyncFieldInfoServiceImpl extends ServiceImpl<SyncFieldInfoMapper, SyncFieldInfo> implements ISyncFieldInfoService {

}
