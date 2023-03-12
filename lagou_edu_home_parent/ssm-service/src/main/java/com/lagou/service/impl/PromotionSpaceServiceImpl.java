package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 广告位业务逻辑层实现类
 */
@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        return promotionSpaceMapper.findAllPromotionSpace();
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        promotionSpace.setIsDel(0);
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(Integer id) {
        return promotionSpaceMapper.findPromotionSpaceById(id);
    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpace.setUpdateTime(new Date());
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
