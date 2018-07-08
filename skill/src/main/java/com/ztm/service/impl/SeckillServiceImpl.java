package com.ztm.service.impl;

import com.ztm.dao.SeckillDao;
import com.ztm.dao.SuccessKilledDao;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    private final String slat = "adfasdfdasf324wer23r32324234!$^%^^$$";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        return seckill;//seckillDao.queryAll(0, 4);
    }





    public Exposer exportSeckillUrl(long seckillId) {
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
        String md5 = getMD5(seckillId);//TODO
        return new Exposer(true,seckillId, md5);//seckillDao.queryAll(0, 4);
    }


    @Transactional
    public SeckillExcution excuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException {

        if(md5 == null || !md5.equals(getMD5(seckillId))){
            throw new SeckillException("seckill data rewrite");
        }
        Date nowTime = new Date();

        try{
            int updataCount = seckillDao.reduceNumber(seckillId, nowTime);

            if( updataCount <= 0 ){
                throw new SeckillCloseException("seckill is closed");
            } else {
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0){
                    throw new RepeatKillException("seckill repeated");
                }else{
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId);
                    return new SeckillExcution(seckillId, 1, "success", successKilled);
                }
            }

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner erroe:" + e.getMessage());
        }
    }

    private String getMD5(long seckillId){
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
