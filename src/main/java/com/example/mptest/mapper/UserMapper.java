package com.example.mptest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mptest.entity.User;
import com.example.mptest.entity.request.UserQueryRequest;
import com.example.mptest.entity.response.UserQueryResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    IPage<UserQueryResponse> selectByName(@Param("p") UserQueryRequest userQueryRequest);
    List<User> selectByNames(@Param("p") User user);

}
