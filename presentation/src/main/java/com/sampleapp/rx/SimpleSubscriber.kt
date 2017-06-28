package com.sampleapp.rx

import rx.Subscriber

open class SimpleSubscriber<T> : Subscriber<T>() {
    override fun onError(e: Throwable?) {
    }

    override fun onNext(value: T) {
    }

    override fun onCompleted() {
    }
}