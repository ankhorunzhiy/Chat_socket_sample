package com.sampleapp.domain.interactor;

import com.sampleapp.domain.data.executor.PostExecutionThread;
import com.sampleapp.domain.data.executor.WorkExecutionThread;
import com.sampleapp.domain.repository.ChatRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;


public class DisconnectUseCase extends UseCase<Void, DisconnectUseCase.Param>{

    private final ChatRepository chatRepository;
    @Inject
    protected DisconnectUseCase(ChatRepository chatRepository, WorkExecutionThread threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.chatRepository = chatRepository;
    }

    @Override
    protected Flowable buildUseCaseObservable(Param aVoid) {
        return chatRepository.disconnect().toFlowable();
    }

    public static DisconnectUseCase mock(ChatRepository chatRepository, WorkExecutionThread threadExecutor, PostExecutionThread postExecutionThread){
        return new DisconnectUseCase(chatRepository, threadExecutor, postExecutionThread);
    }

    public static class Param {
        public static final Param INSTANCE = new Param();
        private Param(){}
    }
}
