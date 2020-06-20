package com.gang.etl.engine.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Classname SyncMainController
 * @Description TODO
 * @Date 2020/2/2 16:50
 * @Created by zengzg
 */
@RestController
@RequestMapping("/main")
public class SyncMainController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    //    @Autowired
    //    private MainLogic mainLogic;
    //
    //    @PostMapping("push/{domain}")
    //    public ResponseModel pushApp(@PathVariable("domain") String domain) {
    //        logger.info("------> this is pushApp <-------");
    //        mainLogic.mainLogic(domain, paramJSON);
    //        return ResponseModel.commonResponse("ok");
    //    }
}
