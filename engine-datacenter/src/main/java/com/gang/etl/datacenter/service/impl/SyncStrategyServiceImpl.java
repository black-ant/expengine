package com.gang.etl.datacenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gang.etl.datacenter.entity.SyncStrategy;
import com.gang.etl.datacenter.mapper.SyncStrategyMapper;
import com.gang.etl.datacenter.service.ISyncStrategyService;
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
public class SyncStrategyServiceImpl extends ServiceImpl<SyncStrategyMapper, SyncStrategy> implements ISyncStrategyService {

}
