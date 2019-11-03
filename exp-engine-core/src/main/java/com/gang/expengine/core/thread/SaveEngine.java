package com.gang.expengine.core.thread;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Classname SaveEngine
 * @Description TODO
 * @Date 2019/11/3 21:40
 * @Created by ant-black 1016930479@qq.com
 */
@Service
public class SaveEngine implements ApplicationRunner, Callable<String> {



    public String run(String path) {
              FutureTask<String> future = new FutureTask<String>(new SaveEngine("a"));
        ExecutorService executor = Executors.newFixedThreadPool(1);


    }


    @Override
    public String call() throws Exception {
        return null;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
