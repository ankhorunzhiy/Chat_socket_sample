package com.android.newssamle.domain.data.repository;

import com.android.newssamle.domain.model.ApiAction;

import rx.Observable;

public interface DataRepositoryInterface {

    Observable performAction(ApiAction action);
}
