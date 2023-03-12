package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.vo.PageVo;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/8
 * @description 菜单模块业务逻辑层接口
 */
public interface MenuService {

    // 查询所有父子级菜单信息
    List<Menu> findAllMenuByPid(Integer id);

    // 查询所有菜单信息
    PageInfo<Menu> findAllMenu(PageVo pageVo);

    // 根据id查询菜单信息
    Menu findMenuById(Integer id);
}
