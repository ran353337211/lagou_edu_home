package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.vo.CourseVo;
import com.lagou.service.CourseService;
import com.lagou.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xumiao
 * @creationTime 2023/3/5
 * @description 课程管理表现层
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 根据条件查询课程信息
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo) {
        List<Course> courseList = courseService.findCourseByCondition(courseVo);
        return new ResponseResult(true,200,"响应成功",courseList);
    }

    /**
     * 文件上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(MultipartFile file, HttpServletRequest request) {

        Map<String, String> map = FileUploadUtil.fileUpload(file, request);
        if (null == map) {
            return new ResponseResult(false,400,"图片上传失败",null);
        } else {
            return new ResponseResult(true,200,"图片上传成功",map);
        }
    }

    /**
     * 新增&修改课程信息及讲师信息
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) {

        if (null == courseVo.getId()){
            courseService.saveCourseAndTeacher(courseVo);
            return new ResponseResult(true,200,"保存成功",null);
        } else {
            courseService.updateCourseAndTeacher(courseVo);
            return new ResponseResult(true,200,"修改成功",null);
        }
    }

    /**
     * 根据id查询课程信息和讲师信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id) {
        CourseVo courseVo = courseService.findCourseById(id);
        return new ResponseResult(true,200,"响应成功",courseVo);
    }

    /**
     * 课程状态管理
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status) {
        courseService.updateCourseStatus(id,status);
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"状态修改成功",map);
    }
}
