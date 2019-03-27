package com.charles.web;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-14
 */
@Controller
@RequestMapping("/loginlog")
public class LoginlogController {
    @RequestMapping("/list")
    public String getPerFiveLoginLog(){
        return "/mims_main.jsp";
    }

}

