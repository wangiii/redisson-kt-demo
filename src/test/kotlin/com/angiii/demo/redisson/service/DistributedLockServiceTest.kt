package com.angiii.demo.redisson.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

/**
 * @author wangchang
 * @date   2021/06/22
 */
@SpringBootTest
class DistributedLockServiceTest {

    @Autowired
    private lateinit var distributedLockService: DistributedLockService

    @Test
    fun lockWithActionTest() {
        val key = UUID.randomUUID().toString()
        
        Thread {
            println(Thread.currentThread().name + " start")
            distributedLockService.lockWithAction(key) {
                println(Thread.currentThread().name + "拿到锁")
                Thread.sleep(3000)
            }
        }.start()
        
        Thread {
            println(Thread.currentThread().name + " start")
            distributedLockService.lockWithAction(key) {
                println(Thread.currentThread().name + "拿到锁")
                Thread.sleep(3000)
            }
        }.start()
        
        Thread.sleep(10000)
    }

}