package com.gang.etl.datacenter.service;

import com.gang.etl.datacenter.entity.SyncLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ant-black
 * @since 2020-09-10
 */
public interface ISyncLogService extends IService<SyncLog> {

    /**
     * 通过 Code 和 status 查询列表
     *
     * @param code
     * @param status
     * @return
     */
    List<SyncLog> findByCodeAndStatus(String code, String status);
}
