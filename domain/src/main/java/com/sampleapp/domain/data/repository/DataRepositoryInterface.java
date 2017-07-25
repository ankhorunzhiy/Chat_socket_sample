package com.sampleapp.domain.data.repository;

import com.sampleapp.domain.model.ApiAction;

import io.reactivex.Observable;


public interface DataRepositoryInterface {

    Observable performAction(ApiAction action);
}
