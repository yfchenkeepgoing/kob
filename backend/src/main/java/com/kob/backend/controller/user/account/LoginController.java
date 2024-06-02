package com.kob.backend.controller.user.account;

import com.kob.backend.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    // 登录一般是post请求，因为密码不适合用明文传输
    // 记得将下面的url在config.SecurityConfig中公开化
    @PostMapping("/user/account/token/")
    public Map<String, String> getToken(@RequestParam Map<String, String> map) {
        // 将post请求中的参数放入map中
        String username = map.get("username");
        String password = map.get("password");
        // 调用接口
        return loginService.getToken(username, password);
    }
}
