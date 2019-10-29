package com.gang.expengine.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("logs")
public class TemplateLogRest extends AbstarctController {

    @GetMapping("get/{key}")
    public List<Object> get(@PathVariable("key") String key) {
        return new LinkedList<>();
    }

    @GetMapping("getAll/{key}")
    public List<Object> getAll(@PathVariable("key") String key) {
        return new LinkedList<>();
    }
}
