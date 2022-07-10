package com.pay2ved.recharge.data.cit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.jmcpapertech.jmcapp.data.cit.HBBaseResponse;
import com.jmcpapertech.jmcapp.data.cit.Settings;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class HBRetrofitCore {

    public static <T, C extends RetrofitSuccessCallback> void call(Observable<T> call, final C callback) {
        call(call, null, callback, null);
    }

    public static <T, SC extends RetrofitSuccessCallback, EC extends RetrofitErrorCallback> void call(Observable<T> call, final SC successCallback, final EC errorCallback) {
        call(call, null, successCallback, errorCallback);
    }

    public static <T, SC extends RetrofitSuccessCallback, EC extends RetrofitErrorCallback> void call(Observable<T> call, final CompositeDisposable disposableContainer, final SC successCallback) {
        call(call, disposableContainer, successCallback);
    }

    public static <T, SC extends RetrofitSuccessCallback, EC extends RetrofitErrorCallback> void call(Observable<T> call, final CompositeDisposable disposableContainer, final SC successCallback, final EC errorCallback) {
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (disposableContainer != null)
                            disposableContainer.add(d);
                    }

                    @Override
                    public void onNext(T response) {
                        if (response instanceof HBBaseResponse && successCallback != null) {
                            Settings settings = ((HBBaseResponse) response).getSettings();
                            if (settings == null) {
                                return;
                            }
                            switch (settings.getSuccess()) {
                                case "1":
                                    successCallback.onSuccess(response);
                                    break;
                                default:
                                    if (successCallback instanceof HBSuccessCallback) {
                                        ((HBSuccessCallback) successCallback).unSuccess(settings.getSuccess(), settings.getMessage());
                                    }

                                    if (successCallback instanceof HBSuccessCallback2) {
                                        ((HBSuccessCallback2) successCallback).unSuccess(settings.getSuccess(), settings.getMessage(), response);
                                    }

                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException exception = (HttpException) e;
                            if (exception.code() == 401) {
                                ResponseBody body = exception.response().errorBody();
                                Gson gson = new Gson();
                                TypeAdapter<HBBaseResponse> adapter = gson.getAdapter
                                        (HBBaseResponse
                                                .class);
                                try {
                                    HBBaseResponse errorParser = adapter.fromJson(body.string());
                                    ((HBSuccessCallback) successCallback).unSuccess("-1",
                                            errorParser.getSettings().getMessage());
                                } catch (Exception e1x) {
                                    e1x.printStackTrace();
                                    ((HBSuccessCallback) successCallback).unSuccess(Settings.NETWORK_ERROR, e.getMessage());
                                }

                            } else {
                                if (successCallback instanceof HBSuccessCallback) {
                                    ((HBSuccessCallback) successCallback).unSuccess(Settings.NETWORK_ERROR, e.getMessage());
                                }
                                if (successCallback instanceof HBSuccessCallback2) {
                                    ((HBSuccessCallback2) successCallback).unSuccess(Settings.NETWORK_ERROR, e.getMessage(), null);
                                }
                            }
                        } else {
                            if (successCallback instanceof HBSuccessCallback) {
                                ((HBSuccessCallback) successCallback).unSuccess(Settings.NETWORK_ERROR, e.getMessage());
                            }
                            if (successCallback instanceof HBSuccessCallback2) {
                                ((HBSuccessCallback2) successCallback).unSuccess(Settings.NETWORK_ERROR, e.getMessage(), null);
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public static <T, SC extends RetrofitSuccessCallback, EC extends RetrofitErrorCallback> void callRetrofitCore(Observable<T> call, final CompositeDisposable disposableContainer, final SC successCallback, final EC errorCallback) {
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (disposableContainer != null)
                            disposableContainer.add(d);
                    }

                    @Override
                    public void onNext(T response) {
                        if (response instanceof HBBaseResponse && successCallback != null) {
                            Settings settings = ((HBBaseResponse) response).getSettings();
                            switch (settings.getSuccess()) {
                                case "1":
                                    successCallback.onSuccess(response);
                                    break;
                                default:
                                    ((HBSuccessCallback) successCallback).unSuccess(settings.getSuccess(), settings.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (errorCallback != null) {
                            errorCallback.onError(e);
                        } else if (successCallback != null && successCallback instanceof HBSuccessCallback) {
                            ((HBSuccessCallback) successCallback).unSuccess(Settings.NETWORK_ERROR, e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}