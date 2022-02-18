

/**
 * 生成递增的Sequence(id)
 * 流程：
 * 1.根据seqKey获取需要生成的Sequence的详情（不存在则获取初始化锁()，进行初始化）
 * 2.获得详情后进行，获取锁，sequence递增。
 * 3.更新详情
 * <p>
 * - mysql实现
 * 1.利用mysql行锁。（unique 的 bizType字段），将流程中的第1步和第2步合二为一。
 * - redis实现
 * 1.在流程中的第一步不仅初始化数据库中的详情，还需要将缓存写到redis。
 * 2.在流程中的第二步实际上仅判断缓存的存在，利用redis先天的原子操作 increase()，无需竞争锁。
 * <p>
 * 所以对于一个未初始化的sequence创建请求，mysql实现方案将产生两次锁的竞争；redis一次。
 * <p>
 * - 已知问题
 * 1. 在竞争锁的时候超时问题 解决方案
 * 2. redis实现的缓存一致性问题
 * - 目前选择更新redis后异步更新数据库，但是无法保证异步更新的成功。
 * - 虽然在创建generator的时候会进行一致性检查，
 */
package com.xh.cloudprovider8002.sn.core;