package com.sampleapp.rx

import io.reactivex.subscribers.DisposableSubscriber

open class SimpleSubscriber<T> : DisposableSubscriber<T>() {

    override fun onNext(value: T) {}

    override fun onError(t: Throwable) {}

    override fun onComplete() {}
}
