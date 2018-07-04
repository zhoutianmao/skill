package com.ztm.service.impl;

import com.ztm.dao.SeckillDao;
import com.ztm.dto.Exposer;
import com.ztm.dto.SeckillExcution;
import com.ztm.entity.Seckill;
import com.ztm.entity.SuccessKilled;
import com.ztm.exception.RepeatKillException;
import com.ztm.exception.SeckillCloseException;
import com.ztm.exception.SeckillException;
import com.ztm.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    private SuccessKilled successKilled;

    private final String slat = "adfasdfdasf324wer23r32324234!$^%^^$$";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Exposer getById(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);

        if (seckill == null){
            return new Exposer(false, seckillId);
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();

        if(nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()){
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        String md5 = null;//TODO

        return new Exposer(true,seckillId, md5);//seckillDao.queryAll(0, 4);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        return null;
    }

    public SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException {
        return null;
    }
}
