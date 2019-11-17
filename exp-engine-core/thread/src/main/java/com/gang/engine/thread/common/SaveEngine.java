package com.gang.engine.thread.common;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @Classname SaveEngine
 * @Description TODO
 * @Date 2019/11/3 21:40
 * @Created by ant-black 1016930479@qq.com
 */
@Service
public class SaveEngine {


    // TODO : NEED ADD SAVE LOGIC
    public String run(ApplicationArguments args) throws Exception {
        FutureTask<String> future = new FutureTask<String>(new EngineMain());
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(future);
        return future.get();
    }

    class EngineMain implements Callable<String> {

        private String path;
        private String file;

        public EngineMain() {
        }

        public EngineMain(String path, String file) {
            this.path = path;
            this.file = file;
        }

        @Override
        public String call() throws Exception {
            return null;
        }

    }


}
