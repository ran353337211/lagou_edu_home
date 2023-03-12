package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;
import com.lagou.domain.User;
import com.lagou.domain.vo.RoleMenuVo;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/8
 * @description 角色模块业务逻辑层接口
 */
public interface RoleService {

    // 查询所有角色（条件）
    List<Role> findAllRole(Role role);

    // 根据角色ID查询关联菜单ID
    List<Integer> findMenuByRoleId(Integer roleId);

    // 角色菜单关联
    void roleContextMenu(RoleMenuVo roleMenuVo, User user);

    // 删除角色
    void deleteRole(Integer id);
}
