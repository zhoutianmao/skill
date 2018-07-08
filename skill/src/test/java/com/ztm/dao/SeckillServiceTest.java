package com.ztm.dao;

import com.ztm.dto.Exposer;
import com.ztm.dto.SeckillExcution;
import com.ztm.entity.Seckill;
import com.ztm.exception.SeckillException;
import com.ztm.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetById() throws Exception{
        long id = 1000;
        Seckill seckill = seckillService.getById(1000);
        logger.info("seckill={}", seckill.getSeckillId());
    }

    @Test
    public void testGetSeckillList() throws Exception{
        List<Seckill> seckills = seckillService.getSeckillList();
        logger.info("lsit={}", seckills);
    }

    @Test
    public void testExportSeckillUrl() throws Exception{
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer = {}", exposer.getMd5());
    }

    @Test
    public void testExecuteSeckill() throws Exception{
        long id = 1000;

        String md5 = "8d3a5ed1795d61727ffac2cf0da90bc8";

        SeckillExcution exception = seckillService.excuteSeckill(id, 1000, md5);
    }
}
