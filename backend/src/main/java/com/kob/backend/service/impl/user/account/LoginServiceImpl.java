package com.kob.backend.service.impl.user.account;

import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.LoginService;
import com.kob.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl extends LoginService {
    // 凡是用到的东西都需要注入
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> getToken(String username, String password) {
        // 将用户名和密码封装为一个类，其中不存储密码的明文，只存储加密后的密码
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        // 用token来登录
        // 输入authenticationManager.authenticate(authenticationToken).var，然后自动补全
        // 如果登录失败，会自动处理
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 没有报异常，则说明登录成功，可以取出用户
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        User user = loginUser.getUser(); // 取出用户

        // 将用户的userID封住为一个jwt token
        // 传入的参数为userid转换为string
        String jwt = JwtUtil.createJWT(user.getId().toString());

        // 返回结果
        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success"); // 所有信息都放入error_message中
        map.put("token", jwt);

        return map;
    }
}
