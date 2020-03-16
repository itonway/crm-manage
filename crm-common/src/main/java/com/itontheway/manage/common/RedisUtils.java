package com.itontheway.manage.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 16:48
     * @Desc 判断key是否存在
     * @Param [key]
     * @Return boolean
     **/
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 16:48
     * @Desc 删除缓存 可以传一个值 或多个
     * @Param [key]
     * @Return void
     **/
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 16:49
     * @Desc 普通缓存获取
     * @Param [key]
     * @Return java.lang.Object
     **/
    public Object get(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return key == null ? null : o;
    }

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/15 16:49
     * @Desc 普通缓存放入
     * @Param [key, value]
     * @Return boolean
     **/
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
