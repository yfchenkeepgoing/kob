package com.kob.backend.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 返回值转换成json格式，然后返回给前端
public class UserController {
    @Autowired // 用到数据库中的Mapper一定要加上本注解
    UserMapper userMapper;

    // 注解@RequestMapping会将所有请求映射过来，请求类型包括get, post
    // 只映射get类型的请求，则用注解@GetMapping
    // 只映射post类型的请求，则用注解@PostMapping
    // 此处用@GetMapping，方便调试
    @GetMapping("/user/all/") // 在本url下返回虽有用户
    public List<User> getAll() {
        return userMapper.selectList(null); // 查询所有用户
    }

    @GetMapping("/user/{userId}/") // 在springboot中传参数的写法: {userId}
    public User getUser(@PathVariable int userId) {
        return userMapper.selectById(userId);
    }

    // 使用条件构造器实现一个较为复杂的API
    // QueryWrapper中有许多API可以用
    @GetMapping("/users/{userId}/")
    public User getUsers(@PathVariable int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return userMapper.selectOne(queryWrapper);
    }

    // 查询某个区间中的用户信息
    @GetMapping("/users/")
    public List<User> getUsers() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("id", 2).le("id", 3);
        return userMapper.selectList(queryWrapper);
    }

    // 插入一个数据，用post，不是明文传输，因此比较安全，body的长度也比较长
    // 但本处为了方便调试，全部用get
    @GetMapping("/user/add/{userId}/{username}/{password}/")
    public String addUser(
            @PathVariable int userId,
            @PathVariable String username,
            @PathVariable String password) {
        User user = new User(userId, username, password);
        userMapper.insert(user);
        return "Add User Successfully";
    }

    // 删除user的api
    @GetMapping("/user/delete/{userId}/")
    public String deleteUser(@PathVariable int userId) {
        userMapper.deleteById(userId);
        return "Delete User Successfully";
    }
}
