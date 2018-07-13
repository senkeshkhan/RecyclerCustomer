package esec.recycler.com.recyclercustomer.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;
import esec.recycler.com.recyclercustomer.CallForCollectorActivity;
import esec.recycler.com.recyclercustomer.CollectingCentersActivity;
import esec.recycler.com.recyclercustomer.InboxActivity;
import esec.recycler.com.recyclercustomer.MyproductsActivity;
import esec.recycler.com.recyclercustomer.ui.profile.ProfileActivity;
import esec.recycler.com.recyclercustomer.R;
import esec.recycler.com.recyclercustomer.RedeemHistoryActivity;
import esec.recycler.com.recyclercustomer.ReferFriendActivity;
import esec.recycler.com.recyclercustomer.RewardPointsActivity;
import esec.recycler.com.recyclercustomer.remote.CommonClass;
import esec.recycler.com.recyclercustomer.remote.PreferenceManager;

import esec.recycler.com.recyclercustomer.ui.base.BaseActivity;
import esec.recycler.com.recyclercustomer.ui.signin.SignInActivity;

public class HomeActivity extends BaseActivity implements HomeMvpView {




    private int dotscount;
    private ImageView[] dots;

    private int delay = 2000; //milliseconds
    Runnable runnable;
    private int page = 0;
    private final Handler handler = new Handler();


    Dialog dialog;

    @Inject
    HomeViewPagerAdapter viewPagerAdapter;

    @BindView(R.id.ll_my_reward_points)
    LinearLayout ll_my_reward_points;


    @BindView(R.id.ll_collection_centers)
    LinearLayout ll_collection_centers;


    @BindView(R.id.ll_my_products)
    LinearLayout ll_my_products;

    @BindView(R.id.ll_reward_history)
    LinearLayout ll_reward_history;

    @BindView(R.id.ll_refer_friends)
    LinearLayout ll_refer_friends;

    @BindView(R.id.ll_call_for_collector)
    LinearLayout ll_call_for_collector;

    @BindView(R.id.SliderDots)
    LinearLayout sliderDotspanel;


    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;



    @Inject
    HomeMvpPresenter<HomeMvpView> mPresenter;





    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(HomeActivity.this);


        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");

        setUp();





    }

    @OnPageChange(R.id.viewPager)
       void onPage(int position ){


       for(int i = 0; i< dotscount; i++){
           dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
       }

       dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));



}



    @OnClick(R.id.ll_my_reward_points)
    void onMyRewardPointsClick(View v) {

        Intent i =RewardPointsActivity. getStartIntent(HomeActivity.this);
        startActivity(i);



    }


    @OnClick(R.id.ll_collection_centers)
    void onCollectionCenterClick(View v) {



        Intent i =CollectingCentersActivity. getStartIntent(HomeActivity.this);
        startActivity(i);


    }

    @OnClick(R.id.ll_my_products)
    void onMyProductsClick(View v) {



        Intent i =MyproductsActivity. getStartIntent(HomeActivity.this);
        startActivity(i);


    }


    @OnClick(R.id.ll_reward_history)
    void onRewardHistoryClick(View v) {


        Intent i =RedeemHistoryActivity. getStartIntent(HomeActivity.this);
        startActivity(i);



    }


    @OnClick(R.id.ll_refer_friends)
    void onReferFriendsClick(View v) {



        Intent i =ReferFriendActivity. getStartIntent(HomeActivity.this);
        startActivity(i);


    }

    @OnClick(R.id.ll_call_for_collector)
    void onCallForCollectorClick(View v) {


        Intent i =CallForCollectorActivity. getStartIntent(HomeActivity.this);
        startActivity(i);



    }


    @OnClick(R.id.img_message)
    void onInboxClick(View v) {


        Intent i =InboxActivity. getStartIntent(HomeActivity.this);
        startActivity(i);



    }

    @Override
    public void onPause() {
        super.onPause();
        // stop auto scroll when onPause
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        // start auto scroll when onResume
        handler.postDelayed(runnable, delay);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_profile) {

            Intent i =ProfileActivity. getStartIntent(HomeActivity.this);
            startActivity(i);
            return true;
        }
        else if (id == R.id.menu_logout) {



            dialog = CommonClass.createConfirmDialog(HomeActivity.this, "", "Do you want to Logout?", "Yes", "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    mPresenter.onLogOutClick();
                    Intent i= new Intent(HomeActivity.this,SignInActivity.class);


                    startActivity(i);
                    finish();


                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            },CommonClass.NO_ICON);
            dialog.show();




           // finishAffinity();
            return true;
        }else if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }





    @Override
    public void onBackPressed() {

       alertBackPress("");


    }


    @Override
    protected void setUp() {



        viewPager.setAdapter(viewPagerAdapter);

        runnable = new Runnable() {
            public void run() {
                if (viewPagerAdapter.getCount() == page) {
                    page = 0;
                } else {
                    page++;
                }
                viewPager.setCurrentItem(page, true);
                handler.postDelayed(this, delay);
            }
        };


        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20*2,     getResources().getDisplayMetrics());

        viewPager.setPageMargin(-margin);






        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

    }


}