package com.gang.etl.engine.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gang.common.lib.to.PageQuery;
import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.entity.SyncLog;
import com.gang.etl.datacenter.service.impl.SyncLogServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname SyncLogController
 * @Description TODO
 * @Date 2020/2/2 15:53
 * @Created by zengzg
 */
@RestController
@RequestMapping("/sync/log")
public class SyncLogController extends AbstratController<SyncLogServiceImpl, SyncLog> {

    @GetMapping("listByPage/{businessId}")
    public ResponseModel businessType(@PathVariable("businessId") String businessId,
                                      @RequestParam("page") Integer page,
                                      @RequestParam("size") Integer size) {


        Page<SyncLog> pages = new Page<>(page, size);
        QueryWrapper<SyncLog> wrapper = new QueryWrapper();
        wrapper.eq("sync_business", businessId);


        IPage<SyncLog> results = baseMapper.page(pages, wrapper);

        return ResponseModel.commonResponse(results);
    }
}
