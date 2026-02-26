package com.tp.complet.order.data;

public class DatabaseRepository implements DataRepository {
    @Override
    public void save(String key, String value) {
        System.out.println("Saving to DB: " + key + " = " + value);
    }
}
