package com.leopie.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/demo")
@Controller
public class DemoController {

    @RequestMapping("/security")
    @ResponseBody
    public String testSecurity() throws Exception{
        return "这是一个功能";
    }
}
