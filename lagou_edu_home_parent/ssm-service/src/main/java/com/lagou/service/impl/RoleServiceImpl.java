package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;
import com.lagou.domain.vo.RoleMenuVo;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/8
 * @description 角色模块业务逻辑层实现类
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        return roleMapper.findMenuByRoleId(roleId);
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        Integer roleId = roleMenuVo.getRoleId();
        // 1.根据roleId删除角色菜单关联信息
        roleMapper.deleteRoleContextMenu(roleId);
        // 2.为角色分配菜单
        Date date = new Date();
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            // 封装数据
            Role_menu_relation rm = new Role_menu_relation();
            rm.setMenuId(mid);
            rm.setRoleId(roleId);
            rm.setCreatedTime(date);
            rm.setUpdatedTime(date);
            rm.setCreatedBy("system");
            rm.setUpdatedby("system");
            roleMapper.roleContextMenu(rm);
        }
    }

    @Override
    public void deleteRole(Integer id) {

        // 清空关联关系
        roleMapper.deleteRoleContextMenu(id);
        // 删除角色
        roleMapper.deleteRole(id);
    }
}
