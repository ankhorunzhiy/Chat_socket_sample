package com.sampleapp.domain.interactor;


import com.sampleapp.domain.data.executor.PostExecutionThread;
import com.sampleapp.domain.data.executor.ThreadExecutor;
import com.sampleapp.domain.data.executor.WorkExecutionThread;
import com.sampleapp.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;


public class RegisterUserUseCase extends UseCase<String, RegisterUserUseCase.Parameters> {

    private final UserRepository userRepository;

    @Inject
    protected RegisterUserUseCase(UserRepository userRepository, WorkExecutionThread threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }


    @Override
    protected Flowable buildUseCaseObservable(Parameters parameters) {
        return userRepository.addUser(parameters.userName);
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
