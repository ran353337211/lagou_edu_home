package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/6
 * @description 课程内容管理业务逻辑层实现类
 */
@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper contentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        return contentMapper.findSectionAndLessonByCourseId(courseId);
    }

    @Override
    public Course findCourseByCourseId(Integer courseId) {
        return contentMapper.findCourseByCourseId(courseId);
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        contentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        courseSection.setUpdateTime(new Date());
        contentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(int id, int status) {
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        section.setUpdateTime(new Date());
        contentMapper.updateSectionStatus(section);
    }

    @Override
    public void saveLesson(CourseLesson courseLesson) {
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        contentMapper.saveLesson(courseLesson);
    }

    @Override
    public void updateLesson(CourseLesson courseLesson) {
        courseLesson.setUpdateTime(new Date());
        contentMapper.updateLesson(courseLesson);
    }
}
