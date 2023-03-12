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

    // 新增资源分类信息
    void saveResourceCategory(ResourceCategory rc);

    // 修改资源分类信息
    void updateResourceCategory(ResourceCategory rc);

    // 1.查询所有相关的资源id集合
    List<Integer> findResourceByCategoryId(Integer categoryId);

    // 2.根据资源id集合删除角色资源关联表中有关资源的信息
    void deleteRoleContextResource(List<Integer> ids);

    // 3.删除资源分类相关的资源信息
    void deleteResourceByCategoryId(Integer categoryId);

    // 4.删除资源分类
    void deleteResourceCategory(Integer id);
}
