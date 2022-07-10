package com.jmcpapertech.jmcapp.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

/**
 * This class is used for storing and retrieving shared preference values.
 */
class AppPreference constructor(
    context: Context
) {
    private val userPreferences: SharedPreferences =
        context.getSharedPreferences("UserPref", Context.MODE_PRIVATE)
    private val rememberPreference: SharedPreferences = context.getSharedPreferences("RememberPref", Context.MODE_PRIVATE)
    private val appPreferences: SharedPreferences =
        context.getSharedPreferences("AppPref", Context.MODE_PRIVATE)
    private val gson = GsonBuilder().create()

    var isLogin: Boolean
        get() = userPreferences.getBoolean("isLogin", false)
        set(value) = userPreferences.edit().putBoolean("isLogin", value).apply()

    var authToken: String?
        get() = userPreferences.getString("auth_token", "")
        set(value) = userPreferences.edit().putString("auth_token", value).apply()

    var deviceToken: String?
        get() = userPreferences.getString("deviceToken", "")
        set(value) = userPreferences.edit().putString("deviceToken", value).apply()

    private fun <T> getObjectFromJsonString(jsonData: String, modelClass: Class<*>): Any? {
        return gson.fromJson(jsonData, modelClass)
    }

    private fun getJsonStringFromObject(modelClass: Any): String {
        return gson.toJson(modelClass)
    }

    var userEmail: String?
        get() = appPreferences.getString("user_email", "")
        set(value) = appPreferences.edit().putString("user_email", value).apply()

    var userImage: String?
        get() = appPreferences.getString("user_Image", "")
        set(value) = appPreferences.edit().putString("user_Image", value).apply()

    var userMobileNumber: String?
        get() = rememberPreference.getString("user_mobile", "")
        set(value) = rememberPreference.edit().putString("user_mobile", value).apply()

    var isCode: String?
        get() = appPreferences.getString("isd_code", "")
        set(value) = appPreferences.edit().putString("isd_code", value).apply()

    var isShowQuickTip: Boolean
        get() = appPreferences.getBoolean("show_quick_tip", false)
        set(value) = appPreferences.edit().putBoolean("show_quick_tip", value).apply()

    var verifyMobile: Boolean
        get() = appPreferences.getBoolean("verify_Mobile", false)
        set(value) = appPreferences.edit().putBoolean("verify_Mobile", value).apply()

    var userId: Int?
        get() = appPreferences.getInt("user_id", 0)
        set(value) = appPreferences.edit().putInt("user_id", value ?: 0).apply()

    var userPassword: String?
        get() = rememberPreference.getString("user_password", "")
        set(value) = rememberPreference.edit().putString("user_password", value).apply()

    var isShowTutorialHome: Boolean
        get() = appPreferences.getBoolean("tutorial_home", true)
        set(value) = appPreferences.edit().putBoolean("tutorial_home", value).apply()

    var rememberMe: Boolean
        get() = rememberPreference.getBoolean("remember_me", false)
        set(value) = rememberPreference.edit().putBoolean("remember_me", value).apply()

    var isShowTutorialSubscriptions: Boolean
        get() = appPreferences.getBoolean("tutorial_subscriptions", true)
        set(value) = appPreferences.edit().putBoolean("tutorial_subscriptions", value).apply()

    var isShowTutorialNewBoard: Boolean
        get() = appPreferences.getBoolean("tutorial_new_board", true)
        set(value) = appPreferences.edit().putBoolean("tutorial_new_board", value).apply()

    var isShowTutorialSearch: Boolean
        get() = appPreferences.getBoolean("tutorial_search", true)
        set(value) = appPreferences.edit().putBoolean("tutorial_search", value).apply()

    var isShowTutorialMyProfile: Boolean
        get() = appPreferences.getBoolean("tutorial_my_profile", true)
        set(value) = appPreferences.edit().putBoolean("tutorial_my_profile", value).apply()

    var followersCounts: String?
        get() = appPreferences.getString("followers_counts", "")
        set(value) = appPreferences.edit().putString("followers_counts", value).apply()

    var followingCounts: String?
        get() = appPreferences.getString("following_counts", "")
        set(value) = appPreferences.edit().putString("following_counts", value).apply()

    var boardsCounts: String?
        get() = appPreferences.getString("boards_counts", "")
        set(value) = appPreferences.edit().putString("boards_counts", value).apply()

    var fullName: String?
        get() = appPreferences.getString("full_name", "")
        set(value) = appPreferences.edit().putString("full_name", value).apply()

    var userName: String?
        get() = appPreferences.getString("user_name", "")
        set(value) = appPreferences.edit().putString("user_name", value).apply()

    /**
     * Used to clear SharedPreferences.
     *
     * @return String
     */
    fun clearUserPreference() {
//        val mobileNumber = userMobileNumber
//        val password = userPassword
//        val remember = rememberMe
        userPreferences.edit().clear().apply()
        appPreferences.edit().clear().apply()
        userPreferences.edit().putBoolean("isLogin", false).apply()
        if (!rememberMe) {
           rememberPreference.edit().clear().apply()
        }
    }
}