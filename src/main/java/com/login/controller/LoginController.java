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
    public UserReq login(@RequestDecode UserReq userReq) {
        return userReq;
    }

    /**
     * 用于方式一  走的切面
     * @param userReq
     * @return
     */
    @PostMapping("/demo")
    @RequestDecode
    public UserReq demo(@RequestBody UserReq userReq) {
        return userReq;
    }
}
