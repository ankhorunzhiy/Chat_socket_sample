package com.sampleapp.domain.interactor;


import com.sampleapp.domain.data.executor.PostExecutionThread;
import com.sampleapp.domain.data.executor.WorkExecutionThread;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.DisposableSubscriber;

public abstract class CachableUseCase<A, P> extends UseCase<A, P> {

    protected CachableUseCase(WorkExecutionThread workExecutionThread, PostExecutionThread postExecutionThread) {
        super(workExecutionThread, postExecutionThread);
    }

    public abstract void subscribe(DisposableSubscriber subscriber);

    public abstract void clearCache();
}
