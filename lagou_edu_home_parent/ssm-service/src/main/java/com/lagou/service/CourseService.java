package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.Teacher;
import com.lagou.domain.vo.CourseVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/5
 * @description 课程管理业务逻辑层
 */
public interface CourseService {

    // 多条件查询课程信息
    public List<Course> findCourseByCondition(CourseVo courseVo);

    // 新增课程信息和讲师信息
    public void saveCourseAndTeacher(CourseVo courseVo);

    // 课程回显（根据id查询相关课程信息和讲师信息）
    public CourseVo findCourseById(Integer id);

    // 修改课程信息和讲师信息
    public void updateCourseAndTeacher(CourseVo courseVo);

    // 课程状态管理
    public void updateCourseStatus(Integer courseId,Integer status);

}
