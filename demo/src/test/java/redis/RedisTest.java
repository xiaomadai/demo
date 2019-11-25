package redis;

import com.xmd.firstBoot.redis.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: xx
 * @Description:
 * @Date: Created in 18:04 2019/11/25
 */
public class RedisTest {


    @Autowired
    RedisUtil redisUtil;

    @Test
    public void contextLoads() {
        System.out.println(redisUtil.set("a", "ceshi"));
        System.out.println(redisUtil.get("a"));
    }

}
