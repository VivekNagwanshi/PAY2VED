package com.jmcpapertech.jmcapp.data.remote

import com.google.gson.JsonElement
import com.jmcpapertech.jmcapp.data.cit.WSGenericResponse

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

/*
    @POST("product_category_list")
    @FormUrlEncoded
    fun getProduct(@FieldMap Request: HashMap<String, String>): Call<ProductCategoryResponse>
*/

    @POST("product_list")
    @FormUrlEncoded
    fun getProductList(@FieldMap Request: HashMap<String, String>): Observable<WSGenericResponse<JsonElement>>
/*

    @POST("product_details")
    @FormUrlEncoded
    fun getProductDetails(@FieldMap Request: HashMap<String, String>): Call<ProductDetailsResponse>

    @POST("unit_master")
    fun getUnitInquiry(): Call<UnitDialogResponse>

    @POST("currency_master_1")
    fun getCurrencyInquiry(): Call<CurrencyDialogResponse>

    @POST("videos")
    fun getVideos(): Call<VideoResponse>

    @POST("favourite_products_list")
    @FormUrlEncoded
    fun getFavourite(@FieldMap Request: HashMap<String, String>): Call<ProductListResponse>

    @POST("make_favourite_unfavourite")
    @FormUrlEncoded
    fun getFavouriteUnFavourite(@FieldMap Request: HashMap<String, String>): Call<ProductInquiryResponse>

    @POST("news_1")
    @FormUrlEncoded
    fun getNews(@FieldMap Request: HashMap<String,String>): Call<NewsResponse>

    @POST("login")
    @FormUrlEncoded
    fun loginPost(@FieldMap dataModal: HashMap<String, String>): Call<LoginResponse?>?

    @POST("sign_up")
    @FormUrlEncoded
    fun singUpPost(@FieldMap dataModal: HashMap<String, String>): Call<SignUpResponse?>?

    @POST("login")
    @FormUrlEncoded
    fun singInPost(@FieldMap dataModal: HashMap<String, String>): Call<LoginResponse?>?

    @POST("forgot_password")
    @FormUrlEncoded
    fun forgetPost(@FieldMap dataModal: HashMap<String, String>): Call<ForgotResponse?>?

    @POST("change_password")
    @FormUrlEncoded
    fun changePassPost(@FieldMap dataModal: HashMap<String, String>): Call<ProductInquiryResponse?>?

    @POST("reset_password")
    @FormUrlEncoded
    fun resetPassPost(@FieldMap dataModal: HashMap<String, String>): Observable<WSGenericResponse<JsonElement>>

    @GET("banner_list")
    fun bannerPost(): Call<BannerResponse?>?

    @POST("send_otp")
    @FormUrlEncoded
    fun sendOtpPost(@FieldMap hashMap: HashMap<String, String>): Call<SendOtpResponse?>?

    @POST("verify_mobile_otp")
    @FormUrlEncoded
    fun mobileVerifyPost(@FieldMap hashMap: HashMap<String, String>): Call<MobileVerifyResponse?>?

    @POST("add_quick_inquiry")
    @FormUrlEncoded
    fun quickInquiryPost(@FieldMap hashMap: HashMap<String, String>): Call<QuickInquiryResPonse?>?

    @POST("product_inquiry_1")
    @FormUrlEncoded
    fun productInquiryPost(@FieldMap hashMap: HashMap<String, String>): Call<ProductInquiryResponse?>?

    @POST("user_profile")
    @FormUrlEncoded
    fun viewProfilePost(@FieldMap hashMap: HashMap<String, String>): Call<ViewProfileResponse>

    @Multipart
    @POST("update_profile_picture")
    fun profileImagePost(
        @Part("user_id") id: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<EditProfilePictureResponse>

    @POST("edit_profile")
    @FormUrlEncoded
    fun editProfilePost(@FieldMap hashMap: HashMap<String, String>): Call<EditProfileResponse>

    @GET("on_hand_projects")
    fun onHandProjectGet(): Call<OnHandProjectResponse?>?

    @GET("valued_client_for_complete_plant")
    fun valuedClientGet(): Call<ValuedClientResponse?>?

    @GET("overseas_client_list")
    fun overseasListGet(): Call<OverseasResponse?>?

    @GET("equipment_supply_list")
    fun equipmentGet(): Call<EquipmentSupplyResponse?>?

    @GET("country_list")
    fun countryListGet(): Call<CountryListResponse?>?

    @GET("company_contact_details")
    fun contactUsGet(): Call<ContactUsResponse>?
*/

}