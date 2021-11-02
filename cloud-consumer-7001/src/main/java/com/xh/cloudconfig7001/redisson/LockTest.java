package com.xh.cloudconfig7001.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author  Xiao Hong
 * date  2021/11/2 22:31
 * description
 */
@Service
public class LockTest {

    @Autowired
    private RedissonClient redissonClient;

    public String testRedisson(){
        // 获取分布式锁，只要锁的名字一样，就是同一把锁
        RLock lock = redissonClient.getLock("lock");

        //加锁（阻塞等待），默认过期时间是30秒
        lock.lock();
        try{
            //如果业务执行过长，Redisson会自动给锁续期
            Thread.sleep(1000);
            System.out.println("加锁成功，执行业务逻辑");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //解锁，如果业务执行完成，就不会继续续期，即使没有手动释放锁，在30秒过后，也会释放锁
            lock.unlock();
        }
        return "Hello Redisson!";
    }
}
