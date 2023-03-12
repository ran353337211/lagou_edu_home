package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 广告位业务逻辑层接口
 */
public interface PromotionSpaceService {

    // 查询所有广告位
    List<PromotionSpace> findAllPromotionSpace();

    // 新增广告位
    void savePromotionSpace(PromotionSpace promotionSpace);

    // 根据id查询广告位名称
    PromotionSpace findPromotionSpaceById(Integer id);

    // 修改广告位
    void updatePromotionSpace(PromotionSpace promotionSpace);
}
