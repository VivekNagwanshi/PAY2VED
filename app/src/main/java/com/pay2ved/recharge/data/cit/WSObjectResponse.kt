package com.jmcpapertech.jmcapp.data.cit

import com.google.gson.annotations.SerializedName

class WSObjectResponse<T> : HBBaseResponse() {
    @SerializedName("data")
    var data: T? = null
}
