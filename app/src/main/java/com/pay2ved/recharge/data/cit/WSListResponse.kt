package com.jmcpapertech.jmcapp.data.cit

import com.google.gson.annotations.SerializedName
import java.util.*

class WSListResponse<T> : HBBaseResponse() {
    @SerializedName("data")
    var data: ArrayList<T>? = null
}
