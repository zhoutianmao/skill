package com.ztm.dao;

import com.ztm.entity.SuccessKilled;

public interface SuccessKilledDao {
    int insertSuccessKilled(long secillId, long userPhone);

    SuccessKilled queryByIdWithSeckill(long seckillId);
}
