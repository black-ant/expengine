package com.gang.etl.engine.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.entity.SyncBean;
import com.gang.etl.datacenter.service.impl.SyncBeanServiceImpl;
import com.gang.etl.engine.web.logic.FieldLogic;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname SyncBeanController
 * @Description TODO
 * @Date 2020/12/4 12:42
 * @Created by zengzg
 */
@RestController
@RequestMapping("sync/bean")
public class SyncBeanController extends AbstratController<SyncBeanServiceImpl, SyncBean> {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FieldLogic fieldInfo;

    @Autowired
    private SyncBeanServiceImpl beanService;

    /**
     * 扫描指定的包拉取 Bean  信息
     *
     * @param type
     * @return
     */
    @GetMapping("scanw")
    public ResponseModel<String> scanPackageBean(@RequestParam(name = "type", required = false) String type) {
        logger.info("------> Select All Field <-------");
        if (StringUtils.isNotEmpty(type)) {
            return ResponseModel.commonResponse(fieldInfo.scanPackage());
        } else {
            return ResponseModel.commonResponse(fieldInfo.scanPackage());
        }
    }

    @GetMapping("query")
    public ResponseModel<List> queryBeanList(@RequestParam(name = "type", required = false) String type) {
        logger.info("------> Select All Field <-------");
        if (StringUtils.isNotEmpty(type)) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("bean_type", type);
            return ResponseModel.commonResponse(beanService.list(wrapper));
        } else {
            return ResponseModel.commonResponse(beanService.list());
        }
    }
}

