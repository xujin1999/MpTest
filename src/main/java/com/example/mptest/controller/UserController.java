package com.example.mptest.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mptest.entity.User;
import com.example.mptest.mapper.UserMapper;
import com.example.mptest.entity.request.UserQueryRequest;
import com.example.mptest.entity.response.UserQueryResponse;
import com.example.mptest.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;
    List<User> users = null;

    @GetMapping("/test")
    public void test() {
//        Assert.notNull(users, "参数为空"); 直接抛异常
//        users = userMapper.selectList(null);
//        users.forEach(System.out::println);

//        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, 2));
//        return user;

//        User user = new User("xujin", 15, "8888@qq.com");
//        userMapper.insert(user);

//        Page<User> userPage = userMapper.selectPage(new Page<>(1, 2), null);
//        System.out.println(userPage);
    }

    @GetMapping("/api/name")
    public IPage<UserQueryResponse> tp(UserQueryRequest userQueryRequest) {
        return userMapper.selectByName(userQueryRequest);
    }

    @GetMapping("/handle")
    public void handleMatter(){
        userService.handle();
    }

    @GetMapping("/api/names")
    public List<User> tpp(User user) {
        return userMapper.selectByNames(user);
    }

}
