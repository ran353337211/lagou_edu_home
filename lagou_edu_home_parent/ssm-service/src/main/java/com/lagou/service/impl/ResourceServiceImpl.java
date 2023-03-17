package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.User;
import com.lagou.domain.vo.ResourceVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/9
 * @description 资源模块业务逻辑层实现类
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo) {

        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());
        List<Resource> list = resourceMapper.findAllResource(resourceVo);
        return new PageInfo<>(list);
    }

    @Override
    public void updateResource(Resource resource, User user) {

        resource.setUpdatedTime(new Date());
        resource.setUpdatedBy(user.getName());
//        resource.setUpdatedBy("system");
        resourceMapper.updateResource(resource);
    }

    @Override
    public void saveResource(Resource resource, User user) {

        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setUpdatedBy(user.getName());
        resource.setCreatedBy(user.getName());
//        resource.setCreatedBy("system");
//        resource.setUpdatedBy("system");
        resourceMapper.saveResource(resource);
    }
}
