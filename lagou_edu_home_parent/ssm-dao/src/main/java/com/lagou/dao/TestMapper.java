package com.lagou.dao;

import com.lagou.domain.Test;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/3
 * @description
 */
public interface TestMapper {

    public List<Test> findAll();
}
