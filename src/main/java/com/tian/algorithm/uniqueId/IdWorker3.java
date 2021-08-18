package com.tian.algorithm.uniqueId;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author David Tian
 * @desc
 * @since 2021/6/28 00:58
 */
public class IdWorker3 {

    private Long workerId;

    private AtomicLong increId;

    public IdWorker3(Long workerId, AtomicLong increId) {
        this.workerId = workerId;
        this.increId = increId;
    }

    public Long nextId(){
        return this.increId.incrementAndGet();
    }

    public Long getWorkerId() {
        return workerId;
    }
}
