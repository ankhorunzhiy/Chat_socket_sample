package com.sampleapp.data.model

import com.google.gson.annotations.SerializedName
import com.sampleapp.domain.model.Event

data class EventDataModel(@SerializedName("username") val userName: String? = null,
                          val message: String? = null, val numUsers: Int? = null, val event: Event? = null)


