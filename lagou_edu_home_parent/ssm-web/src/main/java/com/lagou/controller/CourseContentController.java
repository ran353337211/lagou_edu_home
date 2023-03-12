package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/6
 * @description 课程内容管理表现层
 */
@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService contentService;

    /**
     * 查询课程下的章节与课时信息
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> sectionList = contentService.findSectionAndLessonByCourseId(courseId);
        return new ResponseResult(true,200,"响应成功",sectionList);
    }

    /**
     * 根据课程id查询课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId) {
        Course course = contentService.findCourseByCourseId(courseId);
        return new ResponseResult(true,200,"响应成功",course);
    }

    /**
     * 新增&修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection) {
        if (null == courseSection.getId()) {
            contentService.saveSection(courseSection);
            return new ResponseResult(true,200,"保存成功",null);
        } else {
            contentService.updateSection(courseSection);
            return new ResponseResult(true,200,"更新成功",null);
        }
    }

    /**
     * 修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status) {
        contentService.updateSectionStatus(id,status);
        return new ResponseResult(true,200,"状态修改成功",null);
    }

    /**
     * 新增课时信息
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson) {
        if (null == courseLesson.getId()) {
            contentService.saveLesson(courseLesson);
            return new ResponseResult(true,200,"保存成功",null);
        } else {
            contentService.updateLesson(courseLesson);
            return new ResponseResult(true,200,"修改成功",null);
        }
    }
}
