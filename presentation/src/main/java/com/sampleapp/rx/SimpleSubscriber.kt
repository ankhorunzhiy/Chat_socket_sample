package com.sampleapp.rx

import io.reactivex.subscribers.DisposableSubscriber

open class SimpleSubscriber<T> : DisposableSubscriber<T>() {

    override fun onNext(value: T) {}

    override fun onError(t: Throwable) {}

    override fun onComplete() {}

    companion object{
        @JvmName("alternative_constructor")
        operator fun <T>invoke (onNext: (T) -> Unit): SimpleSubscriber<T>
                = object : SimpleSubscriber<T>() {
            override fun onNext(value: T) {
                onNext.invoke(value)
            }
        }

        @JvmName("alternative_constructor")
        operator fun <T> invoke (onNext: (T) -> Unit, onError: (t: Throwable)-> Unit): SimpleSubscriber<T>
                = object : SimpleSubscriber<T>() {
            override fun onNext(value: T) {
                onNext.invoke(value)
            }

            override fun onError(t: Throwable) {
                onError.invoke(t)
            }
        }

        @JvmName("alternative_constructor")
        operator fun <T> invoke (onNext: (T) -> Unit, onError: (t: Throwable)-> Unit, onComplete: ()-> Unit): SimpleSubscriber<T>
                = object : SimpleSubscriber<T>() {
            override fun onNext(value: T) {
                onNext.invoke(value)
            }

            override fun onError(t: Throwable) {
                onError.invoke(t)
            }

            override fun onComplete() {
                onComplete.invoke()
            }
        }
    }


}
