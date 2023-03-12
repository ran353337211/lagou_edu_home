package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/9
 * @description 资源分类dao层
 */
public interface ResourceCategoryMapper {

    // 查询所有资源分类信息
    List<ResourceCategory> findAllResourceCategory();
}
