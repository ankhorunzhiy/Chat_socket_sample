package com.sampleapp.data.model

import com.google.gson.annotations.SerializedName
import com.sampleapp.domain.model.Event

data class EventDataModel(@SerializedName("username") val userName: String?,
                          val message: String?, val numUsers: Int?, val event: Event?)


