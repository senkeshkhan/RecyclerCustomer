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

package esec.recycler.com.recyclercustomer.ui.home;


import esec.recycler.com.recyclercustomer.di.PerActivity;
import esec.recycler.com.recyclercustomer.ui.base.MvpPresenter;
import esec.recycler.com.recyclercustomer.ui.signin.SignInMvpView;

/**
 * Created by janisharali on 27/01/17.
 */

@PerActivity
public interface HomeMvpPresenter<V extends HomeMvpView> extends MvpPresenter<V> {

    void onLogOutClick();
   /* void onMyRewardPointsClick();
    void onCollectionCenterClick();
    void onMyProductsClick();
    void onRewardHistoryClick();
    void onReferFriendsClick();
    void onCallForCollectorClick();
    void onInboxClick();


    void onProfileClick();
    void onLogoutClick();*/



}