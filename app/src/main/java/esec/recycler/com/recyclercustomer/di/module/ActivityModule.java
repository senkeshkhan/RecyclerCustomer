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

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;


import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import esec.recycler.com.recyclercustomer.data.network.model.BlogResponse;
import esec.recycler.com.recyclercustomer.di.ActivityContext;
import esec.recycler.com.recyclercustomer.di.PerActivity;
import esec.recycler.com.recyclercustomer.ui.blogs.BlogAdapter;
import esec.recycler.com.recyclercustomer.ui.blogs.BlogMvpPresenter;
import esec.recycler.com.recyclercustomer.ui.blogs.BlogMvpView;
import esec.recycler.com.recyclercustomer.ui.blogs.BlogPresenter;
import esec.recycler.com.recyclercustomer.ui.forgetpassword.ForgetPasswordMvpPresenter;
import esec.recycler.com.recyclercustomer.ui.forgetpassword.ForgetPasswordMvpView;
import esec.recycler.com.recyclercustomer.ui.forgetpassword.ForgetPasswordPresenter;

import esec.recycler.com.recyclercustomer.ui.home.HomeMvpPresenter;
import esec.recycler.com.recyclercustomer.ui.home.HomeMvpView;
import esec.recycler.com.recyclercustomer.ui.home.HomePresenter;
import esec.recycler.com.recyclercustomer.ui.home.HomeViewPagerAdapter;
import esec.recycler.com.recyclercustomer.ui.profile.ProfileMvpPresenter;
import esec.recycler.com.recyclercustomer.ui.profile.ProfileMvpView;
import esec.recycler.com.recyclercustomer.ui.profile.ProfilePresenter;
import esec.recycler.com.recyclercustomer.ui.profileedit.ProfileEditMvpPresenter;
import esec.recycler.com.recyclercustomer.ui.profileedit.ProfileEditMvpView;
import esec.recycler.com.recyclercustomer.ui.profileedit.ProfileEditPresenter;
import esec.recycler.com.recyclercustomer.ui.signin.SignInMvpPresenter;
import esec.recycler.com.recyclercustomer.ui.signin.SignInMvpView;
import esec.recycler.com.recyclercustomer.ui.signin.SignInPresenter;
import esec.recycler.com.recyclercustomer.ui.signup.SignUpMvpPresenter;
import esec.recycler.com.recyclercustomer.ui.signup.SignUpMvpView;
import esec.recycler.com.recyclercustomer.ui.signup.SignUpPresenter;
import esec.recycler.com.recyclercustomer.ui.splash.SplashMvpPresenter;
import esec.recycler.com.recyclercustomer.ui.splash.SplashMvpView;
import esec.recycler.com.recyclercustomer.ui.splash.SplashPresenter;
import esec.recycler.com.recyclercustomer.utils.rx.AppSchedulerProvider;
import esec.recycler.com.recyclercustomer.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

   @Provides
    @PerActivity
   SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }




    @Provides
    @PerActivity
    SignInMvpPresenter<SignInMvpView> provideSigninPresenter(
            SignInPresenter<SignInMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    SignUpMvpPresenter<SignUpMvpView> provideSignUpPresenter(
            SignUpPresenter<SignUpMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ForgetPasswordMvpPresenter<ForgetPasswordMvpView> provideForgetPasswordPresenter(
            ForgetPasswordPresenter<ForgetPasswordMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    HomeMvpPresenter<HomeMvpView> provideHomePresenter(
            HomePresenter<HomeMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    ProfileMvpPresenter<ProfileMvpView> provideProfilePresenter(
            ProfilePresenter<ProfileMvpView> presenter) {
        return presenter;
    }
    @Provides
    @PerActivity
    ProfileEditMvpPresenter<ProfileEditMvpView> provideProfileEditPresenter(
            ProfileEditPresenter<ProfileEditMvpView> presenter) {
        return presenter;
    }
    /*@Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }
*/
   /* @Provides
    RatingDialogMvpPresenter<RatingDialogMvpView> provideRateUsPresenter(
            RatingDialogPresenter<RatingDialogMvpView> presenter) {
        return presenter;
    }*/

   /*@Provides
   FeedMvpPresenter<FeedMvpView> provideFeedPresenter(
            FeedPresenter<FeedMvpView> presenter) {
        return presenter;
    }*/

    /*@Provides
    OpenSourceMvpPresenter<OpenSourceMvpView> provideOpenSourcePresenter(
            OpenSourcePresenter<OpenSourceMvpView> presenter) {
        return presenter;
    }*/
    @Provides
    BlogMvpPresenter<BlogMvpView> provideBlogMvpPresenter(
            BlogPresenter<BlogMvpView> presenter) {
        return presenter;
    }
   /* @Provides
    FeedPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }*/

   /* @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>());
    }*/
    @Provides
    BlogAdapter provideBlogAdapter() {
        return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
    }


    @Provides
    HomeViewPagerAdapter provideHomeViewPagerAdapter(AppCompatActivity activity) {
        return new HomeViewPagerAdapter(activity);
    }
 /* @Provides
  BlogMvpPresenter<BlogMvpView> provideBlogMvpPresenter(
          BlogPresenter<BlogMvpView> presenter) {
      return presenter;
  }
  @Provides
  BlogAdapter provideBlogAdapter() {
      return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
  }*/
    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
