package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.vo.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xumiao
 * @creationTime 2023/3/8
 * @description 角色模块表现层
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 条件查询角色信息
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {

        List<Role> roleList = roleService.findAllRole(role);
        return new ResponseResult(true,200,"响应成功",roleList);
    }

    /**
     * 查询所有父子级菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenuByPid() {
        List<Menu> menuList = menuService.findAllMenuByPid(-1);
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        return new ResponseResult(true,200,"响应成功",map);
    }

    /**
     * 根据角色ID查询关联菜单ID
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId) {
        List<Integer> list = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true,200,"响应成功",list);
    }

    /**
     * 为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo) {

        roleService.roleContextMenu(roleMenuVo);
        return new ResponseResult(true,200,"角色分配菜单成功",null);
    }

    /**
     * 删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id) {

        roleService.deleteRole(id);
        return new ResponseResult(true,200,"删除成功",null);
    }
}
