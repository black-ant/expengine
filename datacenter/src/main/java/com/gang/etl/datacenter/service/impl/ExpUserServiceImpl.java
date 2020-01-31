package com.gang.etl.datacenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gang.etl.datacenter.entity.ExpUser;
import com.gang.etl.datacenter.mapper.ExpUserMapper;
import com.gang.etl.datacenter.service.IExpUserService;
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
public class ExpUserServiceImpl extends ServiceImpl<ExpUserMapper, ExpUser> implements IExpUserService {

}
