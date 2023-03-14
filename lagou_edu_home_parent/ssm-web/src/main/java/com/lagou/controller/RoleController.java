package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.domain.vo.RoleMenuVo;
import com.lagou.domain.vo.RoleResourceVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserService userService;

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
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo, HttpServletRequest request) {

        User user = userService.getUser(request);
        roleService.roleContextMenu(roleMenuVo,user);
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

    /**
     * 获取当前角色拥有的资源信息
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId) {

        List<ResourceCategory> categoryList = roleService.findResourceListByRoleId(roleId);
        return new ResponseResult(true,200,"响应成功",categoryList);
    }

    /**
     * 为角色分配资源
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo,HttpServletRequest request) {

        User user = userService.getUser(request);
        roleService.roleContextResource(roleResourceVo,user);
        return new ResponseResult(true,200,"角色分配资源成功",null);
    }

    /**
     * 新增&修改角色
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role,HttpServletRequest request) {

        User user = userService.getUser(request);
        if (null != role.getId()) {
            roleService.updateRole(role,user);
            return new ResponseResult(true, 200, "修改成功", null);
        } else {
            roleService.saveRole(role,user);
            return new ResponseResult(true, 200, "新增成功", null);
        }
    }
}
