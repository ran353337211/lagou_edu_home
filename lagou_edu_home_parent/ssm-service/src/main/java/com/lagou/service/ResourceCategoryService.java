package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/9
 * @description 资源分类业务逻辑层接口
 */
public interface ResourceCategoryService {

    // 查询所有资源分类信息
    List<ResourceCategory> findAllResourceCategory();
}
