package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.domain.vo.RoleMenuVo;
import com.lagou.domain.vo.RoleResourceVo;
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
    public void roleContextMenu(RoleMenuVo roleMenuVo, User user) {

        Integer roleId = roleMenuVo.getRoleId();
        // 1.根据roleId删除角色菜单关联信息
        roleMapper.deleteRoleContextMenu(roleId);
        // 2.为角色分配菜单
        List<Integer> list = roleMenuVo.getMenuIdList();
        if (null != list && list.size() > 0) {
            Date date = new Date();
            // 封装数据
            Role_menu_relation rm = new Role_menu_relation();
            rm.setRoleId(roleId);
            rm.setCreatedTime(date);
            rm.setUpdatedTime(date);
//            rm.setCreatedBy(user.getName());
//            rm.setUpdatedby(user.getName());
            rm.setCreatedBy("system");
            rm.setUpdatedby("system");
            for (Integer mid : list) {
                rm.setMenuId(mid);
                roleMapper.roleContextMenu(rm);
            }
        }

    }

    @Override
    public void deleteRole(Integer id) {

        // 清空关联关系
        roleMapper.deleteRoleContextMenu(id);
        // 删除角色
        roleMapper.deleteRole(id);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {

        // 1.查询所有资源分类信息
        List<ResourceCategory> categoryList = roleMapper.findRoleContextCategory(roleId);
        for (ResourceCategory category : categoryList) {
            // 2.根据roleId和categoryId查询资源列表
            List<Resource> resourceList = roleMapper.findRoleContextResource(roleId, category.getId());
            category.setResourceList(resourceList);
        }
        return categoryList;
    }

    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo, User user) {

        // 1.清空关联关系
        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());
        // 2.新增关联关系
        List<Integer> list = roleResourceVo.getResourceIdList();
        if (null != list && list.size() > 0){
            Role_Resource_relation rr = new Role_Resource_relation();
            rr.setRoleId(roleResourceVo.getRoleId());
            Date date = new Date();
            rr.setCreatedTime(date);
            rr.setUpdatedTime(date);
//            rr.setCreatedBy(user.getName());
//            rr.setUpdatedBy(user.getName());
            rr.setCreatedBy("system");
            rr.setUpdatedBy("system");
            for (Integer resourceId : list) {
                rr.setResourceId(resourceId);
                roleMapper.saveRoleContextResource(rr);
            }
        }
    }

    @Override
    public void updateRole(Role role,User user) {

        role.setUpdatedTime(new Date());
//        role.setUpdatedBy(user.getName());
        role.setUpdatedBy("system");
        roleMapper.updateRole(role);
    }

    @Override
    public void saveRole(Role role,User user) {

        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
//        role.setCreatedBy(user.getName());
//        role.setUpdatedBy(user.getName());
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        roleMapper.saveRole(role);
    }
}
