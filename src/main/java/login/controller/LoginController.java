package login.controller;

import login.common.RequestDecode;
import login.req.UserReq;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author: yuepan
 * @date: 2018/9/21
 */
@RestController
public class LoginController {
    @RequestMapping(name="/login", method = RequestMethod.POST)
    @ResponseBody
    @RequestDecode
    public String login(@RequestBody UserReq userReq) {
        System.out.println(userReq.getName());
        return "潘月";
    }
}
