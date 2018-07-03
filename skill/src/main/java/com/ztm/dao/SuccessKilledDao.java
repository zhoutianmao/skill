package com.ztm.dao;

import com.ztm.entity.SuccessKilled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public interface SuccessKilledDao {
    int insertSuccessKilled(long secillId, long userPhone);

    SuccessKilled queryByIdWithSeckill(long seckillId);
}
