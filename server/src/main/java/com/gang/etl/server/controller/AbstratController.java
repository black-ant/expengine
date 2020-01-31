package com.gang.etl.server.controller;

import com.gang.common.lib.to.AbstractEntity;
import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.mapper.MyBatisBaseMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Classname AbstratController
 * @Description TODO
 * @Date 2020/1/31 22:25
 * @Created by zengzg
 */

public class AbstratController<T extends MyBatisBaseMapper, D extends AbstractEntity> {

    protected MyBatisBaseMapper baseMapper;

    @GetMapping("get/{key}")
    public ResponseModel getSsoConfigEntity(@PathVariable("key") String key) {
        return ResponseModel.commonResponse(baseMapper.getOne(key));
    }

    @GetMapping("getall")
    public ResponseModel getAll() {
        return ResponseModel.commonResponse(baseMapper.findAll());
    }

    @GetMapping("delete")
    public ResponseModel delete(@PathVariable("key") String key) {
        baseMapper.deleteById(key);
        return ResponseModel.commonResponse(key);
    }

    @PostMapping("insert")
    public ResponseModel insert(@RequestBody D entity) {
        return ResponseModel.commonResponse(baseMapper.insert(entity));
    }

    @PostMapping("update")
    public ResponseModel update(@RequestBody D entity) {
        return ResponseModel.commonResponse(baseMapper.update(entity));
    }
}
