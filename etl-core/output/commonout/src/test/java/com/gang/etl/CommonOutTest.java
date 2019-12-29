package com.gang.etl;

import com.gang.etl.common.SyncSDKFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Classname CommonOutTest
 * @Description TODO
 * @Date 2019/12/29 11:44
 * @Created by zengzg
 */
@SpringBootTest(classes = CommonOutApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CommonOutTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncSDKFactory syncSDKFactory;

    @Test
    public void main() {
        syncSDKFactory.getOperationClassName("WORK_WECHAT_ORG");
    }
}
