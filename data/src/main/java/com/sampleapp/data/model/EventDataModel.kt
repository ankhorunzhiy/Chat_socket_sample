package com.sampleapp.data.model

import com.google.gson.annotations.SerializedName

data class EventDataModel(@SerializedName("username") val userName: String?,
                          val message: String?, val numUsers: Int?)


