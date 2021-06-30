package com.tian.algorithm.uniqueId;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author David Tian
 * @desc
 * @since 2021/6/28 00:26
 */
public class IdWorker2 {

    private Long workerId;

    //private Map<Long,Long> map = new HashMap<>();
    private Map<Long,Long> map = new ConcurrentHashMap<>();//非必须要ConcurrentHashMap

    public IdWorker2(Long workerId) {
        this.workerId = workerId;
    }

    public synchronized Long nextId(){
        Long nextId = map.get(workerId);
        if(nextId==null){
            nextId=0L;
        }
        nextId+=1;
        map.put(workerId,nextId);
        return nextId;
    }

    public Long nextId2(){
        synchronized (this.workerId){
            Long nextId = map.get(workerId);
            if(nextId==null){
                nextId=0L;
            }
            nextId+=1;
            map.put(workerId,nextId);
            return nextId;
        }
    }

    public Long getWorkerId() {
        return workerId;
    }
}
