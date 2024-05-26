package com.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 伍六七
 * @date 2023/6/4 17:12
 */
@Controller//return "index" 是跳转页面
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";//如果是"/index"开发没问题,打jar包会出问题
    }
}