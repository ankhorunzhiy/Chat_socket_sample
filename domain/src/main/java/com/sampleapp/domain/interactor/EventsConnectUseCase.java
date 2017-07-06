package com.sampleapp.domain.interactor;

import com.sampleapp.domain.data.executor.PostExecutionThread;
import com.sampleapp.domain.data.executor.ThreadExecutor;
import com.sampleapp.domain.model.EventModel;
import com.sampleapp.domain.repository.ChatRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class EventsConnectUseCase extends UseCase<EventModel, EventsConnectUseCase.Parameters>{

    private final ChatRepository chatRepository;
    @Inject
    protected EventsConnectUseCase(ChatRepository chatRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.chatRepository = chatRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(Parameters params) {
        return chatRepository.on(params.events);
    }

    public static class Parameters{

        private final String[] events;

        public Parameters(String[] events){
            this.events = events;
        }

        public String[] getEvents() {
            return events;
        }
    }
}
