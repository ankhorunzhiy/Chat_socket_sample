package com.sampleapp.data.cache;

import android.util.LruCache;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * Created by Tony on 17.09.17.
 */
@Singleton
public class FlowableCacherImpl implements FlowableCacher {

    private final LruCache<String, Flowable> cache;

    @Inject
    public FlowableCacherImpl() {
        cache = new LruCache<>(4 * 1024 * 1024); //4MiB
    }

    @Override
    public Flowable get(String key) {
        return cache.get(key);
    }

    @Override
    public void put(String key, Flowable value) {
        cache.put(key, value);
    }

    @Override
    public void clear(String key) {
        cache.remove(key);
    }

    @Override
    public boolean contains(String key) {
        return cache.snapshot().keySet().contains(key);
    }
}
