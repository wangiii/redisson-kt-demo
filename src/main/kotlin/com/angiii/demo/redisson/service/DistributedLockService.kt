package com.angiii.demo.redisson.service

/**
 * @author wangchang
 * @date   2021/06/22
 */
interface DistributedLockService {

    fun lockWithAction(lockKey: String, action: () -> Unit)

}