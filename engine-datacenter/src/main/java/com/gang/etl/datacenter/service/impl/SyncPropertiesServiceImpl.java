package com.gang.etl.datacenter.service.impl;

import com.gang.etl.datacenter.entity.SyncProperties;
import com.gang.etl.datacenter.mapper.SyncPropertiesMapper;
import com.gang.etl.datacenter.service.ISyncPropertiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ant-black
 * @since 2021-02-17
 */
@Service
public class SyncPropertiesServiceImpl extends ServiceImpl<SyncPropertiesMapper, SyncProperties> implements ISyncPropertiesService {

}
