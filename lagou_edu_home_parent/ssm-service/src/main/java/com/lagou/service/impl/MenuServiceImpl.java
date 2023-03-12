package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.vo.PageVo;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
