package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.User;
import com.lagou.domain.vo.ResourceVo;
import com.lagou.service.ResourceService;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xumiao
 * @creationTime 2023/3/9
 * @description 资源模块表现层
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;

    /**
     * 资源信息分页&条件查询
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo) {

        PageInfo<Resource> pageInfo = resourceService.findAllResource(resourceVo);
        return new ResponseResult(true,200,"响应成功",pageInfo);
    }

    /**
     * 添加&修改资源信息
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource, HttpServletRequest request) {

        User user = userService.getUser(request);
        if (null != resource.getId()) {
            // 修改
            resourceService.updateResource(resource,user);
            return new ResponseResult(true,200,"修改成功",null);
        } else {
            // 添加
            resourceService.saveResource(resource,user);
            return new ResponseResult(true,200,"添加成功",null);
        }
    }
}
