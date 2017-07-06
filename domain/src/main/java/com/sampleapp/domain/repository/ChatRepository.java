package com.sampleapp.domain.repository;


import com.sampleapp.domain.model.Event;
import com.sampleapp.domain.model.EventModel;

import rx.Observable;

public interface ChatRepository {

    Observable<EventModel> on(Event... events);
}
