package com.zk;

import com.zk.bean.Furn;
import com.zk.service.FurnService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.awt.*;

@SpringBootTest
public class FurnServiceTest {

    @Resource
    private FurnService furnService;

    @Test
    public void t1(){
        furnService.list();
    }
}
