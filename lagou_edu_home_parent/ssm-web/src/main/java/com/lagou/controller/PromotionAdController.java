package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.vo.PageVo;
import com.lagou.service.PromotionAdService;
import com.lagou.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 广告表现层
 */
@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /**
     * 广告分页查询
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PageVo pageVo) {

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllAdByPage(pageVo);
        return new ResponseResult(true,200,"响应成功",pageInfo);
    }

    /**
     * 文件上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(MultipartFile file, HttpServletRequest request) {

        Map<String, String> map = FileUploadUtil.fileUpload(file, request);
        if (null == map) {
            return new ResponseResult(false,105,"图片上传失败",null);
        } else {
            return new ResponseResult(true,200,"图片上传成功",map);
        }
    }

    /**
     * 新增广告信息
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {

        if (null == promotionAd.getId()) {
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"保存成功",null);
        } else {
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true,200,"修改成功",null);
        }
    }

    /**
     * 根据id查询广告信息
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id) {

        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true,200,"响应成功",promotionAd);
    }

    /**
     * 动态广告上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status) {

        promotionAdService.updatePromotionAdStatus(id,status);
        return new ResponseResult(true,200,"上/下线成功",null);
    }
}
