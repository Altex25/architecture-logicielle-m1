package com.tp.complet.order.data;

public class CacheRepository implements DataRepository {
    @Override
    public void save(String key, String value) {
        System.out.println("Saving to Cache: " + key + " = " + value);
    }
}
