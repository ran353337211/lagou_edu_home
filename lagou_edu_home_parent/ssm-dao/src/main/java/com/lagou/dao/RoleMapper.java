package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/8
 * @description 角色模块dao层接口
 */
public interface RoleMapper {

    // 查询所有角色（条件）
    List<Role> findAllRole(Role role);

    // 根据角色ID查询关联菜单ID
    List<Integer> findMenuByRoleId(Integer roleId);

    // 根据roleId删除角色菜单关联信息
    void deleteRoleContextMenu(Integer roleId);

    // 角色菜单关联
    void roleContextMenu(Role_menu_relation rm);

    // 删除角色
    void deleteRole(Integer id);
}
