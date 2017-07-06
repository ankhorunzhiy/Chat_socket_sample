package com.sampleapp.domain.interactor;


import com.sampleapp.domain.data.executor.PostExecutionThread;
import com.sampleapp.domain.data.executor.ThreadExecutor;
import com.sampleapp.domain.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

public class RegisterUserUseCase extends UseCase<String, RegisterUserUseCase.Parameters> {

    private final UserRepository userRepository;

    @Inject
    protected RegisterUserUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }


    @Override
    protected Observable buildUseCaseObservable(Parameters parameters) {
        return userRepository.addUser(parameters.userName);
    }

    public static class Parameters {
        private final String userName;

        public Parameters(String userName) {
            this.userName = userName;
        }
    }

    public static RegisterUserUseCase mock(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new RegisterUserUseCase(userRepository, threadExecutor, postExecutionThread);
    }
}
