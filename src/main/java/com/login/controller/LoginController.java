package com.login.controller;

import com.config.common.RequestDecode;
import com.login.req.UserReq;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author: yuepan
 * @date: 2018/9/21
 */
@RestController
@RequestMapping("/com")
public class LoginController {
    /**
     * 用于请求解密 方式三
     * @param userReq
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestDecode UserReq userReq) {
        System.out.println(userReq);
        return "潘月";
    }

    /**
     * 用于方式一  走的切面
     * @param userReq
     * @return
     */
    @PostMapping("/demo")
    @RequestDecode
    public String demo(@RequestBody UserReq userReq) {
        System.out.println(userReq);
        return "潘月";
    }
}
