package esec.recycler.com.recyclercustomer.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import javax.inject.Inject;

import butterknife.ButterKnife;
import esec.recycler.com.recyclercustomer.ui.home.HomeActivity;
import esec.recycler.com.recyclercustomer.R;
import esec.recycler.com.recyclercustomer.ui.base.BaseActivity;


import esec.recycler.com.recyclercustomer.ui.signin.SignInActivity;
;

public class SplashActivity extends BaseActivity implements SplashMvpView {
    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;
    private static int SPLASH_TIME_OUT = 2500;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(SplashActivity.this);

        //ob.printArray(arr);
    }


    @Override
    public void openLoginActivity() {

        new Handler().postDelayed(new Runnable() {


            /* * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company*/


            @Override
            public void run() {

                Intent intent = SignInActivity.getStartIntent(SplashActivity.this);
                startActivity(intent);
                finish();

              /*  Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(i);
                finish();*/


            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    public void openMainActivity() {



        new Handler().postDelayed(new Runnable() {


            /* * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company*/


            @Override
            public void run() {

                Intent intent = HomeActivity.getStartIntent(SplashActivity.this);
                startActivity(intent);
                finish();
              /*  Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
                System.out.println("11111111111111111111");*/

            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    public void startSyncService() {

    }



    @Override
    protected void setUp() {

    }


}
