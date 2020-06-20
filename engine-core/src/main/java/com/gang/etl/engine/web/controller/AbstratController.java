package com.gang.etl.engine.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gang.common.lib.to.AbstractEntity;
import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.mapper.MyBatisBaseMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname AbstratController
 * @Description TODO
 * @Date 2020/1/31 22:25
 * @Created by zengzg
 */
@Data
public class AbstratController<T extends IService, D extends AbstractEntity> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected T baseMapper;

    @GetMapping("getcount")
    public ResponseModel getCount() {
        return ResponseModel.commonResponse(baseMapper.count());
    }

    @GetMapping("get/{key}")
    public ResponseModel getByKey(@PathVariable("key") String key) {
        return ResponseModel.commonResponse(baseMapper.getById(key));
    }

    @GetMapping("page")
    public ResponseModel getPage(@RequestParam("size") Integer sizeNum, @RequestParam("page") Integer pageNum) {
        Page<D> page = new Page<D>(sizeNum, pageNum);
        logger.info("------> this is page :{} <-------", page);
        return ResponseModel.commonResponse(baseMapper.page(page));
    }

    @GetMapping("list")
    public ResponseModel getAll() {
        return ResponseModel.commonResponse(baseMapper.list());
    }

    @GetMapping("delete")
    public ResponseModel delete(@PathVariable("key") String key) {
        baseMapper.removeById(key);
        return ResponseModel.commonResponse(key);
    }

    @PostMapping("insert")
    public ResponseModel insert(@RequestBody D entity) {
        logger.info("------> this is create new <-------");
        return ResponseModel.commonResponse(baseMapper.save(entity));
    }

    @PostMapping("update")
    public ResponseModel update(@RequestBody D entity) {
        return ResponseModel.commonResponse(baseMapper.update(entity, null));
    }

    @PostMapping("saveorupdate")
    public ResponseModel saveOrUpdate(@RequestBody D entity) {
        return ResponseModel.commonResponse(baseMapper.saveOrUpdate(entity));
    }

}
