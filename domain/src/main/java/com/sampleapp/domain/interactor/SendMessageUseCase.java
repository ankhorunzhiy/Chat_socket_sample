package com.sampleapp.domain.interactor;

import com.sampleapp.domain.data.executor.PostExecutionThread;
import com.sampleapp.domain.data.executor.ThreadExecutor;
import com.sampleapp.domain.model.Event;
import com.sampleapp.domain.model.EventModel;
import com.sampleapp.domain.model.Message;
import com.sampleapp.domain.repository.ChatRepository;

import javax.inject.Inject;

import rx.Observable;

public class SendMessageUseCase extends UseCase<EventModel, SendMessageUseCase.Parameters>{

    private final ChatRepository chatRepository;

    @Inject
    protected SendMessageUseCase(ChatRepository chatRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.chatRepository = chatRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(Parameters params) {
        return chatRepository.sendMessage(params.message);
    }

    public static class Parameters{

        private Message message;
        private Parameters(Message message){
            this.message = message;
        }

        public static Parameters create(Message message){
            return new Parameters(message);
        }

    }
}
