package com.txy.omall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/base")
public class BaseController {
    @RequestMapping("/page")
    public String getIndexPage(){
        return "index.html";
    }

}
