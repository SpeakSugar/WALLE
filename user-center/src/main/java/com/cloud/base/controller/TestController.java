package com.cloud.base.controller;

import beans.ResultBean;
import com.cloud.base.mapper.TestMapper;
import com.cloud.base.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.http.exception.HttpProcessException;

import java.io.FileNotFoundException;

@Controller
@RequestMapping("/spring.url.test")
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestService testService;

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

    @RequestMapping(value = "/testHttpApi")
    @ResponseBody
    public ResultBean testHttpApi() throws HttpProcessException {
        testService.testHttpApi();
        return new ResultBean<>();
    }
}
