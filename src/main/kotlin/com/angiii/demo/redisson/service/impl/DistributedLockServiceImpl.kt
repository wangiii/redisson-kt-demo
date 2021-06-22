package com.angiii.demo.redisson.service.impl

import com.angiii.demo.redisson.service.DistributedLockService
import org.redisson.api.RedissonClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

/**
 * @author wangchang
 * @date   2021/06/22
 */
@Service
class DistributedLockServiceImpl: DistributedLockService {

    @Autowired
    private lateinit var redissonClient: RedissonClient

    @Value("\${prop.redis.label}")
    private lateinit var label: String

    override fun lockWithAction(lockKey: String, action: () -> Unit) {
        val lock = redissonClient.getLock(prepareKey(lockKey))
        try {
            lock.lock()
            action()
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            lock.unlock()
        }
    }

    private fun prepareKey(lockKey: String): String {
        return "${label}:distributed:lock:${lockKey}"
    }
}