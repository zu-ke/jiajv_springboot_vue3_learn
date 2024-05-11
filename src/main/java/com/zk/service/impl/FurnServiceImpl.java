package com.zk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zk.bean.Furn;
import com.zk.mapper.FurnMapper;
import com.zk.service.FurnService;
import org.springframework.stereotype.Service;

@Service
public class FurnServiceImpl extends ServiceImpl<FurnMapper, Furn> implements FurnService {
}
