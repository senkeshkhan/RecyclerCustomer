package esec.recycler.com.recyclercustomer.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import esec.recycler.com.recyclercustomer.ui.profileedit.ProfileEditActivity;
import esec.recycler.com.recyclercustomer.R;
import esec.recycler.com.recyclercustomer.ui.base.BaseActivity;

public class ProfileActivity extends BaseActivity implements ProfileMvpView {


    @BindView(R.id.txt_name)
    TextView txt_name;

    @BindView(R.id.txt_email)
    TextView txt_email;

    @BindView(R.id.txt_phone)
    TextView txt_phone;

    @BindView(R.id.txt_zip)
    TextView txt_zip;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Inject
    ProfileMvpPresenter<ProfileMvpView> mPresenter;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(ProfileActivity.this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");




        setUp();


    }



    @OnClick(R.id.ln_edit)
    void editImageClick(View v){

        Intent i = ProfileEditActivity.getStartIntent(ProfileActivity.this);
        startActivity(i);
    }

    @Override
    protected void setUp() {

        mPresenter.onViewPrepared();
    }

    @Override
    public void onProfileValues(String name, String email, String mobile, String zip) {

        txt_name.setText(name);
        txt_email.setText(email);
        txt_phone.setText(mobile);
        txt_zip.setText(zip);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }


}
