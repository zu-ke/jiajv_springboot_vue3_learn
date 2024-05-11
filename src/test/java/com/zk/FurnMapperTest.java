package com.zk;

import com.zk.mapper.FurnMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class FurnMapperTest {

    @Resource
    private FurnMapper furnMapper;

    @Test
    public void t1(){
        System.out.println(furnMapper.selectById(1));
    }
}
