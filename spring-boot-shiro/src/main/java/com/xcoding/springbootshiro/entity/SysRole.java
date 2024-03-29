package com.xcoding.springbootshiro.entity;

import lombok.Data;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Data
public class SysRole {
    private Integer roleId; // 编号
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Boolean available = Boolean.TRUE; // 是否可用,如果不可用将不会添加给用户

    //角色 -- 权限关系：多对多关系;
    private List<SysPermission> permissions;

    // 用户 - 角色关系定义;
    private List<User> users;// 一个角色对应多个用户
}
