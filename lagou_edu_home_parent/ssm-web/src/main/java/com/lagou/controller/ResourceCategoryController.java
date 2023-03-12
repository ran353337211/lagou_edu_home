package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.User;
import com.lagou.service.ResourceCategoryService;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/9
 * @description 资源分类表现层
 */
@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @Autowired
    private UserService userService;

    /**
     * 查询所有资源分类信息
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {

        List<ResourceCategory> categoryList = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"响应成功",categoryList);
    }

    /**
     * 添加&修改资源分类
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory, HttpServletRequest request) {

        // 获取到操作用户的信息
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        User user = userService.findUserById(userId);
        if (null == resourceCategory.getId()) {
            resourceCategoryService.saveResourceCategory(resourceCategory,user);
            return new ResponseResult(true,200,"新增成功",null);
        } else {
            resourceCategoryService.updateResourceCategory(resourceCategory,user);
            return new ResponseResult(true,200,"修改成功",null);
        }
    }

    /**
     * 删除资源分类
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id) {

        resourceCategoryService.deleteResourceCategory(id);
        return new ResponseResult(true,200,"删除成功",null);
    }
}
