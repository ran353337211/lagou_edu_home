package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 广告位表现层
 */
@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /**
     * 查询所有广告位信息
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace() {

        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();
        return new ResponseResult(true,200,"响应成功",list);
    }

    /**
     * 新增&修改广告位信息
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace) {

        if (null == promotionSpace.getId()) {
            promotionSpaceService.savePromotionSpace(promotionSpace);
            return new ResponseResult(true,200,"保存成功",null);
        } else {
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            return new ResponseResult(true,200,"修改成功",null);
        }
    }

    /**
     * 根据id查询广告位信息
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(Integer id) {

        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
        return new ResponseResult(true,200,"响应成功",promotionSpace);
    }
}
