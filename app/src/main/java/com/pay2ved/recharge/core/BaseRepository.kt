package com.jmcpapertech.jmcapp.core

import android.content.Context
import com.google.gson.JsonElement
import com.jmcpapertech.jmcapp.R
import com.jmcpapertech.jmcapp.data.cit.*
import com.jmcpapertech.jmcapp.data.eventbus.TokenExpire
import io.reactivex.Observable
import org.greenrobot.eventbus.EventBus

abstract class BaseRepository {
    fun <T> getResponse(
        clazz: Observable<T>,
        context: Context,
        listener: (item: WSGenericResponse<JsonElement>?) -> Unit
    ) {
        HBRetrofitCore.call(clazz, object
            : HBSuccessCallback<WSGenericResponse<JsonElement>> {
            override fun onSuccess(response: WSGenericResponse<JsonElement>?) {
                listener(response)
            }

            override fun unSuccess(code: String?, message: String?) {
                if (!handleGeneralResponse(Settings(code ?: "", message ?: ""), context)) {
                    val res = WSGenericResponse<JsonElement>()
                    res.data = null
                    if (code == Settings.NETWORK_ERROR) {
                        res.settings = Settings(
                            code
                                ?: "", context.getString(R.string.something_wrong)
                        )
                    } else {
                        res.settings = Settings(code ?: "", message ?: "")
                    }
                    listener(res)
                }
            }
        }, RetrofitErrorCallback {
            val res = WSGenericResponse<JsonElement>()
            res.data = null
            res.settings = Settings("0", context.getString(R.string.something_wrong))
            listener(res)
        })
    }

    fun handleGeneralResponse(settings: Settings?, context: Context): Boolean {
        if (settings?.isAuthenticationError == true) {
            EventBus.getDefault().post(TokenExpire(settings.message));
            return true
        }
        return false
    }
}