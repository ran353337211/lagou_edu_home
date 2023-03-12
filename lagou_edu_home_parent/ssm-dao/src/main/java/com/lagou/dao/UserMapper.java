package com.lagou.dao;

import com.lagou.domain.*;
import com.lagou.domain.vo.UserVo;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 用户模块dao层接口
 */
public interface UserMapper {

    // 用户分页&条件查询
    List<User> findAllUserByPage(UserVo userVo);

    // 修改用户状态
    void updateUserStatus(User user);

    // 用户登录
    User login(User user);

    // 删除用户关联的角色信息
    void deleteUserContextRole(Integer userId);

    // 分配角色
    void userContextRole(User_Role_relation ur);

    // 分配角色(回显)
    // 1.根据用户id查询关联的角色信息
    List<Role> findUserRoleByUserId(Integer userId);

    // 2.根据角色id集合查询对应的顶级菜单信息
    List<Menu> findParentMenuByRoleId(List<Integer> ids);

    // 3.根据顶级菜单id查询对应的子级菜单信息
    List<Menu> findSubMenuByPid(Integer pid);

    // 4.根据角色id查询对应的资源信息
    List<Resource> findResourceByRoleId(List<Integer> ids);

    // 根据id查询用户信息
    User findUserById(Integer id);
}
