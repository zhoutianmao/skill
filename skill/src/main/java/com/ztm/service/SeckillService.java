package com.ztm.service;

import com.ztm.dto.Exposer;
import com.ztm.dto.SeckillExcution;
import com.ztm.entity.Seckill;

import java.util.List;

public interface SeckillService {

    List<Seckill> getById(long seckillId);

    Exposer exportSeckillUrl(long seckillId);

    SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5) throws Exception;

}
