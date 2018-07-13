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

package esec.recycler.com.recyclercustomer.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import esec.recycler.com.recyclercustomer.BuildConfig;
import esec.recycler.com.recyclercustomer.MvpApp;
import esec.recycler.com.recyclercustomer.R;
import esec.recycler.com.recyclercustomer.data.AppDataManager;
import esec.recycler.com.recyclercustomer.data.DataManager;
import esec.recycler.com.recyclercustomer.data.network.APIService;
import esec.recycler.com.recyclercustomer.data.prefs.AppPreferencesHelper;
import esec.recycler.com.recyclercustomer.data.prefs.PreferencesHelper;
import esec.recycler.com.recyclercustomer.di.ApiInfo;
import esec.recycler.com.recyclercustomer.di.ApplicationContext;
import esec.recycler.com.recyclercustomer.di.DatabaseInfo;
import esec.recycler.com.recyclercustomer.di.PreferenceInfo;
import esec.recycler.com.recyclercustomer.utils.AppConstants;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ApplicationModule {

    private final MvpApp mApplication;



   /* public ApplicationModule(Application application) {
        mApplication = application;
    }*/
    public ApplicationModule(MvpApp baseApplication) {
        this.mApplication = baseApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }
    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }
    @Provides
    @Singleton
    public APIService provideApiService() {
        return APIService.Factory.create(mApplication);
    }
   /* @Provides
    @Singleton
    public ApiHelper provideApiService() {
        return ApiHelper.Factory.create(mApplication);
    }*/
   /* @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }
*/
    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }
    /*@Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }*/
   /* @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }*/

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
