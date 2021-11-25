package com.xh.cloudconfig7001.redisson;

import org.redisson.RedissonMultiLock;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * author  Xiao Hong
 * date  2021/11/2 22:31
 * description
 */
@Service
public class LockTest {

    @Autowired
    private RedissonClient redissonClient;

    public String testRedisson() {
        // 获取分布式锁，只要锁的名字一样，就是同一把锁
        RLock lock = redissonClient.getLock("lock");

        //加锁（阻塞等待），默认过期时间是30秒
        lock.lock();
        try {
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

    public void testFairLock(RedissonClient redisson) {
        RLock fairLock = redisson.getFairLock("anyLock");
        try {
            // 最常见的使用方法
            fairLock.lock();
            // 支持过期解锁功能, 10秒钟以后自动解锁,无需调用unlock方法手动解锁
            fairLock.lock(10, TimeUnit.SECONDS);
            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
            boolean res = fairLock.tryLock(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fairLock.unlock();
        }

    }


    /**
     * Redisson的RedissonMultiLock对象可以将多个RLock对象关联为一个联锁，每个RLock对象实例可以来自于不同的Redisson实例。
     */
    public void testMultiLock() {

        RLock lock1 = redissonClient.getLock("lock1");
        RLock lock2 = redissonClient.getLock("lock2");
        RLock lock3 = redissonClient.getLock("lock3");

        RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);

        try {
            // 同时加锁：lock1 lock2 lock3, 所有的锁都上锁成功才算成功。
            lock.lock();

            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
            boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public  void mainRCountDownLatch() {
        RCountDownLatch latch = redissonClient.getCountDownLatch("anyCountDownLatch");
        latch.trySetCount(1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 在其他线程或其他JVM里
        RCountDownLatch latch2 = redissonClient.getCountDownLatch("anyCountDownLatch");
        latch.countDown();
    }
}
