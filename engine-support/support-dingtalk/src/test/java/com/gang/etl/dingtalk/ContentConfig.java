package com.gang.etl.dingtalk;

import com.gang.etl.dingtalk.logic.DingTalkOrgLogic;
import org.junit.Before;
import org.springframework.context.annotation.Bean;

/**
 * @Classname ContentConfig
 * @Description TODO
 * @Date 2020/12/6 12:00
 * @Created by zengzg
 */
public class ContentConfig {

    @Bean
    public DingTalkOrgLogic getDingTalkOrgLogic() {
        return new DingTalkOrgLogic();
    }
}
