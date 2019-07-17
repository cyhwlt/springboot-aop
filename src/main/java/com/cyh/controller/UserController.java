package com.cyh.controller;

import com.cyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 访问路径 http://localhost:10000/user/findUserNameByTel?tel=1234567
     * @param tel 手机号
     * @return userName
     */
    @ResponseBody
    @RequestMapping("/findUserNameByTel")
    public String findUserNameByTel(@RequestParam("tel") String tel){
        return userService.findUserName(tel);
    }
}
