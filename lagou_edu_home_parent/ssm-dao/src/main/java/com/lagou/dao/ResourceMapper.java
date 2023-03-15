package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.vo.ResourceVo;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/9
 * @description 资源模块dao层接口
 */
public interface ResourceMapper {

    //  资源信息分页&条件查询
    List<Resource> findAllResource(ResourceVo resourceVo);

    // 修改资源信息
    void updateResource(Resource resource);

    // 添加资源信息
    void saveResource(Resource resource);
}
