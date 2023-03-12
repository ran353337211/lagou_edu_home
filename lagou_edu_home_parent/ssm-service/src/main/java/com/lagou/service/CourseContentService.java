package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/6
 * @description 课程内容管理业务逻辑层
 */
public interface CourseContentService {

    // 查询课程下的章节与课时信息
    List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    // 根据课程id查询课程信息
    Course findCourseByCourseId(Integer courseId);

    // 新增章节信息
    void saveSection(CourseSection courseSection);

    // 更新章节信息
    void updateSection(CourseSection courseSection);

    // 修改状态信息
    void updateSectionStatus(int id, int status);

    // 新增课时信息
    void saveLesson(CourseLesson courseLesson);

    // 修改课时信息
    void updateLesson(CourseLesson courseLesson);
}
