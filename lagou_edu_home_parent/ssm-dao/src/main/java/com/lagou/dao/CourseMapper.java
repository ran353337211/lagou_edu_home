package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.Teacher;
import com.lagou.domain.vo.CourseVo;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/5
 * @description 课程管理dao层
 */
public interface CourseMapper {

    // 多条件查询课程信息
    List<Course> findCourseByCondition(CourseVo courseVo);

    // 新增课程信息
    void saveCourse(Course course);

    // 新增教师信息
    void saveTeacher(Teacher teacher);

    // 课程回显（根据id查询相关课程信息和讲师信息）
    CourseVo findCourseById(Integer id);

    // 修改课程信息
    void updateCourse(Course course);

    // 修改教师信息
    void updateTeacher(Teacher teacher);

    // 课程状态管理
    void updateCourseStatus(Course course);
}
