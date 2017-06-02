package com.sampleapp.domain.data.repository;

import com.sampleapp.domain.model.ApiAction;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Anton Khorunzhiy on 6/1/17.
 */
public class DataRepositoryImpl implements DataRepositoryInterface {

    @Inject
    public DataRepositoryImpl(){}

    @Override
    public Observable performAction(ApiAction action) {
        return Observable.empty();  // ToDo change implementation when it will be ready
    }
}
