package com.ztm.web;

import com.ztm.dto.Exposer;
import com.ztm.dto.SeckillExcution;
import com.ztm.dto.SeckillResult;
import com.ztm.entity.Seckill;
import com.ztm.exception.RepeatKillException;
import com.ztm.exception.SeckillCloseException;
import com.ztm.exception.SeckillException;
import com.ztm.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller


@RequestMapping("/seckill")
public class SeckillCOntroller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SeckillService seckillService;

    @RequestMapping(name = "list", method = RequestMethod.GET)
    public String list(Model modele){
       List<Seckill> seckills = seckillService.getSeckillList();
       modele.addAttribute("list", seckills);

        return "list";
    }


    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if(seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if(seckill == null){
            return "forward:/seckill/list";
        }

        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public SeckillResult<Exposer> exposer(Long seckillId){

        SeckillResult<Exposer> result;

        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExcution> execute(@PathVariable("seckillId") Long seckillId,
                                                        @PathVariable("md5") String md5,
                                                        @CookieValue(value = "killPhone", required = false) Long phone){

        if(phone == null){
            return new SeckillResult<SeckillExcution>(false, "no regirsger");
        }

        SeckillResult<SeckillExcution> result;

        try {
            SeckillExcution execution = seckillService.excuteSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExcution>(true, execution);
        }catch (SeckillCloseException e1){
            //throw e1;
            //SeckillExcution excution = new SeckillExcution(seckillId, "");

        }catch (RepeatKillException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new SeckillResult("seckill inner error:" + e.getMessage());
        }


        return null;
    }
}
