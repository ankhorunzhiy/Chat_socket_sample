package com.sampleapp.domain.interactor;


import com.sampleapp.domain.data.executor.PostExecutionThread;
import com.sampleapp.domain.data.executor.ThreadExecutor;
import com.sampleapp.domain.data.repository.DataRepositoryInterface;
import com.sampleapp.domain.model.ApiAction;

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
