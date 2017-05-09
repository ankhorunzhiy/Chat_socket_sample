package com.android.newssamle.domain.interactor;


import com.android.newssamle.domain.data.executor.PostExecutionThread;
import com.android.newssamle.domain.data.executor.ThreadExecutor;
import com.android.newssamle.domain.data.repository.DataRepositoryInterface;
import com.android.newssamle.domain.model.ApiAction;

import javax.inject.Inject;

import rx.Observable;

public class ApiUseCase<T extends ApiAction> extends UseCase<T> {
    private final DataRepositoryInterface dataRepository;

    @Inject
    protected ApiUseCase(DataRepositoryInterface dataRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.dataRepository = dataRepository;
    }


    @Override
    protected Observable buildUseCaseObservable(T action) {
        return dataRepository.performAction(action);
    }
}
