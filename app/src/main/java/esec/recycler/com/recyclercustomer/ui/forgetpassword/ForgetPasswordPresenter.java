/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package esec.recycler.com.recyclercustomer.ui.forgetpassword;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;

import javax.inject.Inject;

import esec.recycler.com.recyclercustomer.data.DataManager;
import esec.recycler.com.recyclercustomer.data.network.model.ForgetPasswordModel;
import esec.recycler.com.recyclercustomer.ui.base.BasePresenter;

import esec.recycler.com.recyclercustomer.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by janisharali on 27/01/17.
 */

public class ForgetPasswordPresenter<V extends ForgetPasswordMvpView> extends BasePresenter<V>
        implements ForgetPasswordMvpPresenter<V> {

    private static final String TAG = "ForgetPasswordPresenter";

    @Inject
    public ForgetPasswordPresenter(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onServerForgetPasswordClick(String email, String mobile) {
        //validate email and password
       /* if (email == null || email.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.invalid_email);
            return;
        }
        if (mobile == null || mobile.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }*/
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .sendForgetPassword(email,mobile)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ForgetPasswordModel>() {
                    @Override
                    public void accept(ForgetPasswordModel response) throws Exception {





                            Log.e("Success", new Gson().toJson(response.getMessage()));



                            if(response.getSuccess()==1){



                                getMvpView().alertDialogRegister(response.getMessage());

                            }else{

                                getMvpView().alertDialogSingle(response.getMessage());


                            }






                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                       // getMvpView().openSignInActivity();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }



}
