package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.vo.PageVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 广告业务逻辑层实现类
 */
@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo<PromotionAd> findAllAdByPage(PageVo pageVo) {

        // 分页查询
        PageHelper.startPage(pageVo.getCurrentPage(),pageVo.getPageSize());
        List<PromotionAd> adList = promotionAdMapper.findAllAdByPage();
        return new PageInfo<>(adList);
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {

        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(Integer id) {
        return promotionAdMapper.findPromotionAdById(id);
    }

    @Override
    public void updatePromotionAdStatus(Integer id,Integer status) {

        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
