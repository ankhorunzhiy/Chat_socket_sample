package com.sampleapp.domain.data.repository;

import com.sampleapp.domain.model.ApiAction;

import rx.Observable;

public interface DataRepositoryInterface {

    Observable performAction(ApiAction action);
}
