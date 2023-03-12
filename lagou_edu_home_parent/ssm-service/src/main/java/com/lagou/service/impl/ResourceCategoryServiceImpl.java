package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.User;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/9
 * @description 资源分类业务逻辑层实现类
 */
@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceCategoryMapper.findAllResourceCategory();
    }

    @Override
    public void saveResourceCategory(ResourceCategory rc, User user) {

        Date date = new Date();
        rc.setCreatedTime(date);
        rc.setUpdatedTime(date);
        rc.setCreatedBy(user.getName());
        rc.setUpdatedBy(user.getName());
        resourceCategoryMapper.saveResourceCategory(rc);
    }

    @Override
    public void updateResourceCategory(ResourceCategory rc,User user) {

        rc.setUpdatedTime(new Date());
        rc.setUpdatedBy(user.getName());
        resourceCategoryMapper.updateResourceCategory(rc);
    }

    @Override
    public void deleteResourceCategory(Integer id) {

        // 1.查询所有相关的资源id集合
        List<Integer> ids = resourceCategoryMapper.findResourceByCategoryId(id);
        if (null != ids && ids.size() > 0) {
            // 2.删除角色资源关联表中有关资源的信息
            resourceCategoryMapper.deleteRoleContextResource(ids);
            // 3.删除资源分类相关的资源信息
            resourceCategoryMapper.deleteResourceByCategoryId(id);
        }
        // 4.删除资源分类
        resourceCategoryMapper.deleteResourceCategory(id);
    }
}
