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

package esec.recycler.com.recyclercustomer.di.component;



import dagger.Component;
import esec.recycler.com.recyclercustomer.di.PerActivity;
import esec.recycler.com.recyclercustomer.di.module.ActivityModule;
import esec.recycler.com.recyclercustomer.ui.blogs.BlogActivity;
import esec.recycler.com.recyclercustomer.ui.forgetpassword.ForgetPasswordActivity;

import esec.recycler.com.recyclercustomer.ui.home.HomeActivity;
import esec.recycler.com.recyclercustomer.ui.profile.ProfileActivity;
import esec.recycler.com.recyclercustomer.ui.profileedit.ProfileEditActivity;
import esec.recycler.com.recyclercustomer.ui.signin.SignInActivity;
import esec.recycler.com.recyclercustomer.ui.signup.SignUpActivity;
import esec.recycler.com.recyclercustomer.ui.splash.SplashActivity;

;


/**
 * Created by janisharali on 27/01/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity activity);


    void inject(SignInActivity activity);

    void inject(SignUpActivity activity);
    void inject(HomeActivity activity);
    void inject(ProfileActivity activity);
    void inject(ProfileEditActivity activity);
    //void inject(OpenSourceFragment fragment);

    void inject(BlogActivity fragment);
    void inject(ForgetPasswordActivity activity);

   /* void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(FeedActivity activity);

    void inject(AboutFragment fragment);

    void inject(OpenSourceFragment fragment);

    void inject(BlogActivity fragment);

    void inject(RateUsDialog dialog);*/

}
