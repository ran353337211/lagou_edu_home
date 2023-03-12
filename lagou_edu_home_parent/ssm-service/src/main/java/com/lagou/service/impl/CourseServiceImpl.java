package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.Teacher;
import com.lagou.domain.vo.CourseVo;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/5
 * @description 课程管理业务逻辑层实现类
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        return courseMapper.findCourseByCondition(courseVo);
    }

    @Override
    public void saveCourseAndTeacher(CourseVo courseVo){

        try {
            // 封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(course,courseVo);
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);

            courseMapper.saveCourse(course);

            // 封装讲师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVo);
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            teacher.setCourseId(course.getId());

            courseMapper.saveTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CourseVo findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseAndTeacher(CourseVo courseVo) {

        try {
            // 封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(course,courseVo);
            Date date = new Date();
            course.setUpdateTime(date);
            courseMapper.updateCourse(course);

            // 封装讲师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVo);
            teacher.setCourseId(course.getId());
            teacher.setUpdateTime(date);
            courseMapper.updateTeacher(teacher);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCourseStatus(Integer courseId, Integer status) {

        Course course = new Course();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        courseMapper.updateCourseStatus(course);
    }
}
