package com.login.controller;

import com.config.decodeAni.DecodeParamEnable;
import com.login.req.UserReq;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public UserReq login(@DecodeParamEnable UserReq userReq) {
        return userReq;
    }

    /**
     * 用于方式一  走的切面
     * @param userReq
     * @return
     */
    @PostMapping("/demo")
    @DecodeParamEnable
    public UserReq demo(@RequestBody @Valid UserReq userReq) {
        return userReq;
    }
}
