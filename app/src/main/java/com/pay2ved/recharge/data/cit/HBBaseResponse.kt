package com.jmcpapertech.jmcapp.data.cit

import com.google.gson.annotations.SerializedName

open class HBBaseResponse {
    @SerializedName("settings")
    var settings: Settings? = null
}
