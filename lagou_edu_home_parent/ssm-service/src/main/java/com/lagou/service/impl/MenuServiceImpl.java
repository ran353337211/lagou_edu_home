package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.User;
import com.lagou.domain.vo.PageVo;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/8
 * @description 菜单模块业务逻辑层实现类
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAllMenuByPid(Integer id) {
        return menuMapper.findAllMenuByPid(id);
    }

    @Override
    public PageInfo<Menu> findAllMenu(PageVo pageVo) {

        PageHelper.startPage(pageVo.getCurrentPage(),pageVo.getPageSize());
        List<Menu> menuList = menuMapper.findAllMenu();
        return new PageInfo<>(menuList);
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }

    @Override
    public void updateMenu(Menu menu, User user) {

        if (menu.getParentId() == -1) {
            menu.setLevel(0);
        } else {
            menu.setLevel(1);
        }
        menu.setUpdatedTime(new Date());
//        menu.setUpdatedBy(user.getName());
        menu.setUpdatedBy("system");
        menuMapper.updateMenu(menu);
    }

    @Override
    public void saveMenu(Menu menu, User user) {

        if (menu.getParentId() == -1) {
            menu.setLevel(0);
        } else {
            menu.setLevel(1);
        }
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
//        menu.setCreatedBy(user.getName());
//        menu.setUpdatedBy(user.getName());
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menuMapper.saveMenu(menu);
    }

    @Override
    public void deleteMenu(Integer id) {

        // 1.判断是父菜单还是子菜单，若是父菜单则需删除对应的子菜单
        Menu menu = menuMapper.findMenuById(id);
        if (null != menu) {
            // 获取所有父子菜单id
            List<Integer> menuIds = new ArrayList<>();
            menuIds.add(menu.getId());
            if (menu.getParentId() == -1) {
                // 是父级菜单
                List<Menu> allMenuByPid = menuMapper.findAllMenuByPid(menu.getId());
                for (Menu menu1 : allMenuByPid) {
                    menuIds.add(menu1.getId());
                }
            }
            // 2.删除对应的角色菜单关联关系
            menuMapper.deleteRoleContextMenuByMenuIds(menuIds);
            // 3.删除菜单
            menuMapper.deleteMenuByMenuIds(menuIds);
        }
    }
}
