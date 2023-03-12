package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.vo.PageVo;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 广告业务逻辑层接口
 */
public interface PromotionAdService {

    // 分页查询广告
    PageInfo<PromotionAd> findAllAdByPage(PageVo pageVo);

    // 新增广告信息
    void savePromotionAd(PromotionAd promotionAd);

    // 修改广告信息
    void updatePromotionAd(PromotionAd promotionAd);

    // 根据id查询广告信息
    PromotionAd findPromotionAdById(Integer id);

    // 动态广告上下线
    void updatePromotionAdStatus(Integer id,Integer status);
}
