package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.domain.vo.UserVo;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 用户模块业务逻辑层实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVo);
        return new PageInfo<>(userList);
    }

    @Override
    public void updateUserStatus(Integer id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdateTime(new Date());
        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {

        User loginUser = userMapper.login(user);
        if (null != loginUser && Md5.verify(user.getPassword(),"lagou",loginUser.getPassword())) {
            // 校验成功
            return loginUser;
        } else {
            // 校验失败
            return null;
        }
    }

    @Override
    public List<Role> findUserRoleByUserId(Integer userId) {
        return userMapper.findUserRoleByUserId(userId);
    }

    @Override
    public void userContextRole(UserVo userVo,User user) {

        Integer userId = userVo.getUserId();

        // 1.清空关联关系
        userMapper.deleteUserContextRole(userId);
        // 2.遍历角色集合，新增关联关系
        User_Role_relation ur = new User_Role_relation();
        ur.setUserId(userId);
        Date date = new Date();
        ur.setCreatedTime(date);
        ur.setUpdatedTime(date);
        ur.setCreatedBy(user.getName());
        ur.setUpdatedby(user.getName());
        for (Integer roleId : userVo.getRoleIdList()) {
            ur.setRoleId(roleId);
            userMapper.userContextRole(ur);
        }
    }

    @Override
    public Map<String,Object> getUserPermissions(Integer userId) {

        // 1.根据用户id查询关联的角色信息
        List<Role> roleList = userMapper.findUserRoleByUserId(userId);
        // 获取到关联角色的id集合
        List<Integer> ids = new ArrayList<>();
        for (Role role : roleList) {
            ids.add(role.getId());
        }

        // 2.根据角色id集合查询对应的顶级菜单信息
        List<Menu> menuList = userMapper.findParentMenuByRoleId(ids);

        // 3.根据顶级菜单id查询对应的子级菜单信息 并封装到menu中
        for (Menu menu : menuList) {
            List<Menu> subMenuList = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenuList);
        }

        // 4.根据角色id查询对应的资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(ids);

        // 5.封装map
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",menuList);
        map.put("resourceList",resourceList);
        return map;
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }
}
