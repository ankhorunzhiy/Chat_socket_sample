package com.sampleapp.domain.interactor;


import com.sampleapp.domain.data.executor.PostExecutionThread;
import com.sampleapp.domain.data.executor.WorkExecutionThread;
import com.sampleapp.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subscribers.DisposableSubscriber;


public class RegisterUserUseCase extends CachableUseCase<String, RegisterUserUseCase.Parameters> {

    private final UserRepository userRepository;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable(); //Todo provide from dagger

    @Inject
    protected RegisterUserUseCase(UserRepository userRepository, WorkExecutionThread threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }


    @Override
    protected Flowable buildUseCaseObservable(Parameters parameters) {
        return userRepository.addUserSingle(parameters.userName, true).toFlowable();
    }

    @Override
    public void subscribe(DisposableSubscriber subscriber) {
        compositeDisposable.add(userRepository.provideCached().toFlowable().subscribeWith(subscriber));
    }

    @Override
    public void clearCache() {
        userRepository.clearCachedUser();
    }

    public static class Parameters {
        private final String userName;

        public Parameters(String userName) {
            this.userName = userName;
        }
    }

    public static RegisterUserUseCase mock(UserRepository userRepository, WorkExecutionThread threadExecutor, PostExecutionThread postExecutionThread){
        return new RegisterUserUseCase(userRepository, threadExecutor, postExecutionThread);
    }
}
