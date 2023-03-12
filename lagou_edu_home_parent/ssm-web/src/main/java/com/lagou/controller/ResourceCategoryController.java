package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 查询所有资源分类信息
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {

        List<ResourceCategory> categoryList = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"响应成功",categoryList);
    }
}
