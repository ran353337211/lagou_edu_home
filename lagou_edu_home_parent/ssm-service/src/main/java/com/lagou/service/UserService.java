package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 用户模块业务逻辑层接口
 */
public interface UserService {

    // 用户分页&条件查询
    PageInfo<User> findAllUserByPage(UserVo userVo);

    // 修改用户状态
    void updateUserStatus(Integer id,String status);

    // 用户登录
    User login(User user) throws Exception;

    // 分配角色(回显)
    List<Role> findUserRoleByUserId(Integer userId);

    // 分配角色
    void userContextRole(UserVo userVo);

    // 获取用户拥有的菜单权限
    Map<String,Object> getUserPermissions(Integer userId);
}