package com.example.mptest.entity.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class UserQueryRequest extends Page<UserQueryRequest> {
    private String name;

    private Integer age;

    private String email;
}
