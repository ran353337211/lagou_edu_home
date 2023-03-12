package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 广告dao层接口
 */
public interface PromotionAdMapper {

    // 分页查询广告信息及其对应的广告位信息
    List<PromotionAd> findAllAdByPage();

    // 新增广告信息
    void savePromotionAd(PromotionAd promotionAd);

    // 修改广告信息
    void updatePromotionAd(PromotionAd promotionAd);

    // 根据id查询广告信息
    PromotionAd findPromotionAdById(Integer id);

    // 动态广告上下线
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
