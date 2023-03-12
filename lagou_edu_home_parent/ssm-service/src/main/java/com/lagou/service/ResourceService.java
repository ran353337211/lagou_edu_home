package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.vo.ResourceVo;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/9
 * @description 资源模块业务逻辑层接口
 */
public interface ResourceService {

    //  资源信息分页&条件查询
    PageInfo<Resource> findAllResource(ResourceVo resourceVo);
}
