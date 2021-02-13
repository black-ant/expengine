package com.gang.etl.dingtalk;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.dingtalk.logic.DingTalkOrgLogic;
import com.gang.etl.dingtalk.setting.DingtalkSetting;
import com.gang.etl.engine.api.query.BaseQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Classname OrgTest
 * @Description TODO
 * @Date 2020/12/6 11:05
 * @Created by zengzg
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContentConfig.class})
public class OrgTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DingTalkOrgLogic logic;

    @Test
    public void test() {
        DingtalkSetting setting = new DingtalkSetting();
        setting.setAgentId(Long.valueOf(317940527));
        setting.setAppkey("dingy9pckovmud17ws0r");
        setting.setAppsecret("sBg8Tjd5R5-qCgUhUxEfZFiUcpJpDJXRfQvY4hNPZuvA4k6JR_2wOKXGqulIUHI_");
        setting.setCorpid("ding89501d9e6a2091b435c2f4657eb6378f");

        BaseQuery query = new BaseQuery();
        query.setFilter("1");
        try{
            List list = logic.getOrgList(setting, query);
            logger.info("------> this is orgList :{} <-------", JSONObject.toJSONString(list));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
