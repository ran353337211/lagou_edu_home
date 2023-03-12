package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.vo.PageVo;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xumiao
 * @creationTime 2023/3/9
 * @description 菜单模块表现层
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询所有菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(PageVo pageVo) {

        PageInfo<Menu> pageInfo = menuService.findAllMenu(pageVo);
        return new ResponseResult(true,200,"响应成功",pageInfo);
    }

    /**
     * 查询菜单信息(回显)
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuById(Integer id) {

        // 父级菜单信息，下拉框
        List<Menu> menuList = menuService.findAllMenuByPid(-1);
        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        // 判断id的值，如果是新增菜单,则id值为 -1,修改菜单，则为当前选择的id值
        if (-1 == id) {
            map.put("menuInfo",null);
        } else {
            Menu menu = menuService.findMenuById(id);
            map.put("menuInfo",menu);
        }
        return new ResponseResult(true,200,"响应成功",map);
    }
}
