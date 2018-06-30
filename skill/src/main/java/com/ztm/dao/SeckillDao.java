package com.ztm.dao;

import com.ztm.entity.Seckill;

import java.util.Date;
import java.util.List;

public interface SeckillDao {
    int reduceNumber(long seckillId, Date killTime);

    Seckill queryById(long seckillId);

    List<Seckill> queryAll(int offset, int limit);
}
