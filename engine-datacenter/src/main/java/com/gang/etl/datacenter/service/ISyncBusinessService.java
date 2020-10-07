package com.gang.etl.datacenter.service;

import com.gang.etl.datacenter.entity.SyncBusiness;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ant-black
 * @since 2020-06-27
 */
public interface ISyncBusinessService extends IService<SyncBusiness> {

    SyncBusiness findByCode(String code);

}
