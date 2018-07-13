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

package esec.recycler.com.recyclercustomer.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import esec.recycler.com.recyclercustomer.data.DataManager;
import esec.recycler.com.recyclercustomer.di.ApplicationContext;
import esec.recycler.com.recyclercustomer.di.PreferenceInfo;
import esec.recycler.com.recyclercustomer.utils.AppConstants;


/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";


    private static final String PREF_KEY_CURRENT_CUSTOMER_NAME = "PREF_KEY_CURRENT_CUSTOMER_NAME";

    private static final String PREF_KEY_CURRENT_CUSTOMER_ID = "PREF_KEY_CURRENT_CUSTOMER_ID";

    private static final String PREF_KEY_CURRENT_CUSTOMER_EMAIL = "PREF_KEY_CURRENT_CUSTOMER_EMAIL";

    private static final String PREF_KEY_CURRENT_CUSTOMER_MOBILE = "PREF_KEY_CURRENT_CUSTOMER_MOBILE";

    private static final String PREF_KEY_CURRENT_CUSTOMER_ZIP = "PREF_KEY_CURRENT_CUSTOMER_ZIP";

    private static final String PREF_KEY_CURRENT_CUSTOMER_REWARDPOINT = "PREF_KEY_CURRENT_CUSTOMER_REWARDPOINT";

    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL
            = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }





    ////////////////////////////////////////////////


    @Override
    public void setCustomerName(String customerName) {

        mPrefs.edit().putString(PREF_KEY_CURRENT_CUSTOMER_NAME,customerName).apply();
    }

    @Override
    public String getCustomerName() {
        return mPrefs.getString(PREF_KEY_CURRENT_CUSTOMER_NAME,"");
    }


    @Override
    public void setCustomer_Id(String customer_id) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_CUSTOMER_ID,customer_id).apply();
    }

    @Override
    public String getCustomer_Id() {
        return mPrefs.getString(PREF_KEY_CURRENT_CUSTOMER_ID,"");
    }

    @Override
    public void setCustomerEmail(String customerEmail) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_CUSTOMER_EMAIL,customerEmail).apply();
    }

    @Override
    public String getCoustomerEmail() {
        return mPrefs.getString(PREF_KEY_CURRENT_CUSTOMER_EMAIL,"");
    }

    @Override
    public void setMobile(String mobile) {

        mPrefs.edit().putString(PREF_KEY_CURRENT_CUSTOMER_MOBILE,mobile).apply();

    }

    @Override
    public String getMobile() {
        return mPrefs.getString(PREF_KEY_CURRENT_CUSTOMER_MOBILE,"");
    }

    @Override
    public void setZip(String zip) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_CUSTOMER_ZIP,zip).apply();
    }

    @Override
    public String getZip() {
        return mPrefs.getString(PREF_KEY_CURRENT_CUSTOMER_ZIP,"");
    }

    @Override
    public void setRewardPoint(String rewardPoint) {

        mPrefs.edit().putString(PREF_KEY_CURRENT_CUSTOMER_REWARDPOINT,rewardPoint).apply();

    }

    @Override
    public String getRewardPoint() {
        return mPrefs.getString(PREF_KEY_CURRENT_CUSTOMER_REWARDPOINT,"");
    }
}
