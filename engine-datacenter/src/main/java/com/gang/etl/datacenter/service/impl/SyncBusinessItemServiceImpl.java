package com.gang.etl.datacenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.etl.datacenter.entity.SyncBusiness;
import com.gang.etl.datacenter.entity.SyncBusinessItem;
import com.gang.etl.datacenter.mapper.SyncBusinessItemMapper;
import com.gang.etl.datacenter.service.ISyncBusinessItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ant-black
 * @since 2020-11-30
 */
@Service
public class SyncBusinessItemServiceImpl extends ServiceImpl<SyncBusinessItemMapper, SyncBusinessItem> implements ISyncBusinessItemService {


    /**
     * @param code
     * @return
     */
    public SyncBusinessItem findByItemCode(String code) {
        QueryWrapper<SyncBusinessItem> wrapper = new QueryWrapper();
        wrapper.eq("itemCode", code);
        return getOne(wrapper);
    }
}
