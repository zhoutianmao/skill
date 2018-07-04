package com.ztm.service;

import com.ztm.dto.Exposer;
import com.ztm.dto.SeckillExcution;
import com.ztm.entity.Seckill;
import com.ztm.exception.RepeatKillException;
import com.ztm.exception.SeckillCloseException;
import com.ztm.exception.SeckillException;

import java.util.List;

public interface SeckillService {

    List<Seckill> getSeckillList();

    public Exposer getById(long seckillId);

    Exposer exportSeckillUrl(long seckillId);

    SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException;

}
