package com.jmcpapertech.jmcapp.data.cit

import com.google.gson.annotations.SerializedName

class WSGenericResponse<T> : HBBaseResponse() {
    @SerializedName("data")
    var data: T? = null
}
