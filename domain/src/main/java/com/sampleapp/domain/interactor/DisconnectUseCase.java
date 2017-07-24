package com.sampleapp.domain.interactor;

import com.sampleapp.domain.data.executor.PostExecutionThread;
import com.sampleapp.domain.data.executor.WorkExecutionThread;
import com.sampleapp.domain.model.Event;
import com.sampleapp.domain.model.EventModel;
import com.sampleapp.domain.repository.ChatRepository;

import javax.inject.Inject;

import rx.Observable;

public class DisconnectUseCase extends UseCase<Void, Void>{

    private final ChatRepository chatRepository;
    @Inject
    protected DisconnectUseCase(ChatRepository chatRepository, WorkExecutionThread threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.chatRepository = chatRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(Void aVoid) {
        return chatRepository.disconnect();
    }

    public static DisconnectUseCase mock(ChatRepository chatRepository, WorkExecutionThread threadExecutor, PostExecutionThread postExecutionThread){
        return new DisconnectUseCase(chatRepository, threadExecutor, postExecutionThread);
    }
}
