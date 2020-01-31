package com.gang.etl.datacenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.mapper.SyncTypeMapper;
import com.gang.etl.datacenter.service.ISyncTypeService;
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
public class SyncTypeServiceImpl extends ServiceImpl<SyncTypeMapper, SyncType> implements ISyncTypeService {

}
