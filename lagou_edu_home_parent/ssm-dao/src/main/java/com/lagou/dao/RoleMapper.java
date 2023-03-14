package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

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

    // 查询当前角色拥有的资源分类信息
    List<ResourceCategory> findRoleContextCategory(Integer roleId);

    // 查询当前角色拥有的资源信息
    List<Resource> findRoleContextResource(@Param("roleId") Integer roleId, @Param("categoryId") Integer categoryId);

    // 根据roleId删除角色资源关联信息
    void deleteRoleContextResource(Integer roleId);

    // 角色资源关联
    void saveRoleContextResource(Role_Resource_relation rr);

    // 修改角色
    void updateRole(Role role);

    // 新增角色
    void saveRole(Role role);
}
