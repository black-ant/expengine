package com.gang.etl.datacenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.etl.datacenter.entity.SyncBusiness;
import com.gang.etl.datacenter.mapper.SyncBusinessMapper;
import com.gang.etl.datacenter.service.ISyncBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ant-black
 * @since 2020-06-27
 */
@Service
public class SyncBusinessServiceImpl extends ServiceImpl<SyncBusinessMapper, SyncBusiness> implements ISyncBusinessService {

    public SyncBusiness findByCode(String code) {
        QueryWrapper<SyncBusiness> wrapper = new QueryWrapper();
        wrapper.eq("id", code).or().eq("business_code", code);
        return getOne(wrapper);
    }

}
