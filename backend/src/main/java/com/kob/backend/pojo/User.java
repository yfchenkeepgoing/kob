package com.kob.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 用lombok自动实现构造函数
@Data // 将常用的getter, to_string等函数自动填充好
@NoArgsConstructor // 无参构造函数
@AllArgsConstructor // 所有参数构造函数

public class User {
    // 将User表翻译成class
    // 最好不用int，而用对象，否则在mybatis-plus中会有warning
    private Integer id;
    private String username;
    private String password;
}
