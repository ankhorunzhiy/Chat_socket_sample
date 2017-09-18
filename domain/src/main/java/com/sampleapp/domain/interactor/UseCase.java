/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sampleapp.domain.interactor;

import com.sampleapp.domain.data.executor.PostExecutionThread;
import com.sampleapp.domain.data.executor.WorkExecutionThread;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a {@link Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase<A, P> {

    private final WorkExecutionThread workExecutionThread;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable compositeDisposable;

    protected UseCase(WorkExecutionThread workExecutionThread,
                      PostExecutionThread postExecutionThread) {
        this.workExecutionThread = workExecutionThread;
        this.postExecutionThread = postExecutionThread;
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Builds an {@link Flowable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Flowable buildUseCaseObservable(P params);

    /**
     * Executes the current use case.
     *
     * @param observer The guy who will be listen to the observable build
     *                 with {@link #buildUseCaseObservable(P additional params)}.
     */
    @SuppressWarnings("unchecked")
    public void execute(FlowableSubscriber<A> observer, P params) {
        final Flowable flowable = buildUseCaseObservable(params)
                .subscribeOn(workExecutionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
        compositeDisposable.add((Disposable) flowable.subscribeWith(observer));
    }

    /**
     * Unsubscribes from current {@link Disposable}.
     */
    public void dispose() {
        if(!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }


}
