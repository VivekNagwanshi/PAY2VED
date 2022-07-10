package com.pay2ved.recharge.data.cit;

public interface HBSuccessCallback2<T> extends RetrofitSuccessCallback<T> {
    void onSuccess(T response);

    void unSuccess(String code, String message, T response);
}