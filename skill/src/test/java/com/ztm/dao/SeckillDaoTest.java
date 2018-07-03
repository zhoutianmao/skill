package com.ztm.dao;

import com.ztm.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;


    @Test
    public void testReduceNumber() throws Exception{
        long id = 1000;
        System.out.println(seckillDao);
        Seckill seckill = seckillDao.queryById(id);

        System.out.println(seckill.getName());
    }
}
