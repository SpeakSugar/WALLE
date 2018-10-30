package com.cloud.base.controller;

import beans.ResultBean;
import com.cloud.base.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/spring.url.test")
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping(value = "/helloWorld")
    @ResponseBody
    public ResultBean<String> helloWorld() {
        return new ResultBean<>("hello,spring");
    }

    @RequestMapping(value = "/testDbConnect")
    @ResponseBody
    public ResultBean<String> testDbConnect() {
        return new ResultBean<>(testMapper.testQuery());
    }
}
