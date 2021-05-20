package com.filos.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastFactory {
    private static HazelcastInstance hazelcastInstance;
    private static final Object LOCK = new Object();

    private HazelcastFactory() {
    }

    public static HazelcastInstance getInstance() {
        synchronized (LOCK) {
            if (hazelcastInstance == null) {
                hazelcastInstance = Hazelcast.newHazelcastInstance();
            }
            return hazelcastInstance;
        }
    }

}
