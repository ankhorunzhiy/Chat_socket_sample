package com.sampleapp.data.cache;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;

/**
 * Created by Tony on 17.09.17.
 */

public interface FlowableCacher {

    @Nullable
    Flowable get(String key);
    void put(String key, Flowable value);
    void clear(String key);
    boolean contains(String key);
}
