package com.gang.etl.server.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gang.common.lib.to.AbstractEntity;
import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.mapper.MyBatisBaseMapper;
import lombok.Data;
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
@Data
public class AbstratController<T extends IService, D extends AbstractEntity> {

    protected IService baseMapper;

    @GetMapping("get/{key}")
    public ResponseModel getSsoConfigEntity(@PathVariable("key") String key) {
        return ResponseModel.commonResponse(baseMapper.getById(key));
    }

    @GetMapping("getall")
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
        return ResponseModel.commonResponse(baseMapper.save(entity));
    }

    @PostMapping("update")
    public ResponseModel update(@RequestBody D entity) {
        return ResponseModel.commonResponse(baseMapper.update(entity, null));
    }
}
