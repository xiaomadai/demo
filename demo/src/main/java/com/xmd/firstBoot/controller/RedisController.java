package com.xmd.firstBoot.controller;

import com.xmd.firstBoot.redis.utils.RedisUtil;
import com.xmd.firstBoot.response.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: GCG
 * @Description:
 * @Date: Created in 18:10 2019/11/25
 */
@RestController
@RequestMapping(value = {"/api/xmd/demo/v1/redis"}, produces = {"application/json;charset=UTF-8"})
public class RedisController {

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/test")
    public CommonResult<String> contextLoads() {
        redisUtil.set("a", "ceshi");
        String s = (String) redisUtil.get("a");
        return new CommonResult<String>(s);
    }


}
