package com.lagou.service.impl;

import com.lagou.dao.TestMapper;
import com.lagou.domain.Test;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xumiao
 * @creationTime 2023/3/3
 * @description
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> findAll() {
        return testMapper.findAll();
    }
}
