package com.lagou.service;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.User;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/9
 * @description 资源分类业务逻辑层接口
 */
public interface ResourceCategoryService {

    // 查询所有资源分类信息
    List<ResourceCategory> findAllResourceCategory();

    // 新增资源分类信息
    void saveResourceCategory(ResourceCategory rc, User user);

    // 修改资源分类信息
    void updateResourceCategory(ResourceCategory rc,User user);

    // 删除资源分类
    void deleteResourceCategory(Integer id);
}
