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

package esec.recycler.com.recyclercustomer.data;


import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;


import esec.recycler.com.recyclercustomer.data.network.APIService;
import esec.recycler.com.recyclercustomer.data.network.model.BlogResponse;




import esec.recycler.com.recyclercustomer.data.prefs.PreferencesHelper;
import esec.recycler.com.recyclercustomer.di.ApplicationContext;
import esec.recycler.com.recyclercustomer.data.network.model.ForgetPasswordModel;
import esec.recycler.com.recyclercustomer.data.network.model.LoginResponseModel;
import esec.recycler.com.recyclercustomer.data.network.model.SingUpResponseModel;
import esec.recycler.com.recyclercustomer.data.network.model.ProfileUpdateModel;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;

    private final PreferencesHelper mPreferencesHelper;

    private final APIService mAPIService;

    @Inject
    public AppDataManager(@ApplicationContext Context context, PreferencesHelper preferencesHelper, APIService apiService) {
        mContext = context;

        mPreferencesHelper = preferencesHelper;

        mAPIService = apiService;
    }





    @Override
    public Observable<BlogResponse> getForecastForCity(){

        return mAPIService.getForecastForCity();
    }

    @Override
    public Observable<LoginResponseModel> sendSignInPost(String email, String password) {
        return mAPIService.sendSignInPost(email,password);
    }

    @Override
    public Observable<SingUpResponseModel> sendSignUp(String name, String email, String mobile, String password, String zip, String user_type, String address, String age, String gender) {
        return mAPIService.sendSignUp(name,email,mobile,password,zip,user_type,address,age,gender);
    }

    @Override
    public Observable<ForgetPasswordModel> sendForgetPassword(String email, String mobile) {
        return mAPIService.sendForgetPassword(email,mobile);
    }


    @Override
    public Observable<ProfileUpdateModel> uploadFile(MultipartBody.Part file, RequestBody name, RequestBody zip, RequestBody customer_id) {
        return mAPIService.uploadFile(file, name, zip, customer_id);
    }

    @Override
    public Observable<ProfileUpdateModel> uploadFile1(RequestBody name, RequestBody zip, RequestBody customer_id) {
        return mAPIService.uploadFile1(name, zip, customer_id);
    }

    @Override
    public void setCustomerName(String customerName) {
        mPreferencesHelper.setCustomerName(customerName);
    }

    @Override
    public String getCustomerName() {
        return mPreferencesHelper.getCustomerName();
    }

    @Override
    public void setCustomer_Id(String customer_id) {
        mPreferencesHelper.setCustomer_Id(customer_id);
    }

    @Override
    public String getCustomer_Id() {
        return mPreferencesHelper.getCustomer_Id();
    }

    @Override
    public void setCustomerEmail(String customerEmail) {
        mPreferencesHelper.setCustomerEmail(customerEmail);
    }

    @Override
    public String getCoustomerEmail() {
        return mPreferencesHelper.getCoustomerEmail();
    }

    @Override
    public void setMobile(String mobile) {
        mPreferencesHelper.setMobile(mobile);
    }

    @Override
    public String getMobile() {
        return mPreferencesHelper.getMobile();
    }

    @Override
    public void setZip(String zip) {
        mPreferencesHelper.setZip(zip);
    }

    @Override
    public String getZip() {
        return mPreferencesHelper.getZip();
    }

    @Override
    public void setRewardPoint(String rewardPoint) {

        mPreferencesHelper.setRewardPoint(rewardPoint);
    }

    @Override
    public String getRewardPoint() {
        return mPreferencesHelper.getRewardPoint();
    }




}
