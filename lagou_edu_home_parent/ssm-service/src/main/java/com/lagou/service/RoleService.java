package com.lagou.service;

import com.lagou.domain.*;
import com.lagou.domain.vo.RoleMenuVo;
import com.lagou.domain.vo.RoleResourceVo;

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

    // 获取当前角色拥有的资源信息
    List<ResourceCategory> findResourceListByRoleId(Integer roleId);

    // 为角色分配资源
    void roleContextResource(RoleResourceVo roleResourceVo, User user);
}
