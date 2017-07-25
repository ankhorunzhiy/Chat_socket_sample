package com.sampleapp.domain.repository;


import com.sampleapp.domain.model.Event;
import com.sampleapp.domain.model.EventModel;
import com.sampleapp.domain.model.Message;

import io.reactivex.Completable;
import io.reactivex.Flowable;


public interface ChatRepository {

    Flowable<EventModel> on(Event... events);

    Flowable<EventModel> sendMessage(Message message);

    Completable disconnect();
}
