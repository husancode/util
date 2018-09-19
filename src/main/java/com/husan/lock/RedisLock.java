package com.husan.lock;


import javax.annotation.Resource;

/**
 * <p>
 *     redis分布式锁
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/6/29
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class RedisLock {

    @Resource
    private IRedisClientTemplate redisClientTemplate;

    /**
     *
     * @param lockKey
     * @param lockTime
     * @return
     */
    public boolean tryLock(String lockKey, int lockTime){
        if(redisClientTemplate.setnx(lockKey, LOCK_VALUE) == 1){
            redisClientTemplate.expire(lockKey, lockTime);
            return true;
        }
        return false;
    }


    public void releaseLock(String lockKey){
        redisClientTemplate.del(lockKey);
    }

    private static final String LOCK_VALUE = "1";


}
