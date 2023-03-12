package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/8
 * @description 菜单模块dao层接口
 */
public interface MenuMapper {

    // 查询所有父子级菜单信息
    List<Menu> findAllMenuByPid(Integer pid);

    // 查询所有菜单信息
    List<Menu> findAllMenu();

    // 根据id查询菜单信息
    Menu findMenuById(Integer id);
}

