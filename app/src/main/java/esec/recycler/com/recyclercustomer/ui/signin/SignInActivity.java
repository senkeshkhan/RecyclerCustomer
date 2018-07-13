package esec.recycler.com.recyclercustomer.ui.signin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import esec.recycler.com.recyclercustomer.ui.forgetpassword.ForgetPasswordActivity;
import esec.recycler.com.recyclercustomer.ui.home.HomeActivity;
import esec.recycler.com.recyclercustomer.R;
import esec.recycler.com.recyclercustomer.ui.signup.SignUpActivity;
import esec.recycler.com.recyclercustomer.ui.base.BaseActivity;

import static esec.recycler.com.recyclercustomer.remote.CommonClass.isValidEmail;

public class SignInActivity extends BaseActivity implements SignInMvpView,LocationListener {



    Context context;

    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;


    LocationManager locationManager;

   // double lat,lon;
    String mprovider;





    @Inject
    SignInMvpPresenter<SignInMvpView> mPresenter;


    @BindView(R.id.ed_email)
    EditText mEmailEditText;

    @BindView(R.id.ed_password)
    EditText mPasswordEditText;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(SignInActivity.this);

        context = SignInActivity.this;








        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           // checkPermission();

        } else {
            // write your logic here
        }




    }

    @OnClick(R.id.btn_signin)
    void onServerSigInClick(View v) {

        if(validate()){
            mPresenter.onServerSigInClick(mEmailEditText.getText().toString(),
                    mPasswordEditText.getText().toString());
        }else{

        }


    }
    @OnClick(R.id.bt_register)
    void onRegisterClick(View v) {
        mPresenter.onRegisterClick();

    }


    @OnClick(R.id.bt_forget)
    void onForgetPasswordClick(View v) {
       mPresenter.onForgetPasswordClick();

    }

    @Override
    public void openHomeActivity() {
        Intent intent = HomeActivity.getStartIntent(SignInActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }


    @Override
    public void openRegisterActivity() {

         Intent intent = SignUpActivity.getStartIntent(SignInActivity.this);
                startActivity(intent);
                finish();
    }

    @Override
    public void openForgetPasswordActivity() {

        Intent intent = ForgetPasswordActivity.getStartIntent(SignInActivity.this);
        startActivity(intent);
        finish();

    }

    private boolean validate() {
        boolean temp=true;
        String checkemail = mEmailEditText.getText().toString();
        String pass=mPasswordEditText.getText().toString();



        //

           if(checkemail.length()<1){
               mEmailEditText.setError("Please enter email id");
               temp=false;
           }else if(!isValidEmail(checkemail)){
            mEmailEditText.setError("Please enter a valid email id");
            temp=false;
          }



              if(pass.length()<1){

                 mPasswordEditText.setError("Please enter  password");

                  temp=false;
             }else if(!isNetworkConnected()){



            alertDialogSingle("Network not available");




            temp=false;
        }
       return temp;
    }


/*

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }
*/






    @Override
    public void onBackPressed() {


        alertBackPress("");

        }




    @Override
    public void onLocationChanged(Location location) {


       // lat=location.getLatitude();
       // lon=location.getLongitude();

       // System.out.println("oooooooooooooo"+location.getLatitude());
        //textViewLongitude.setText("Longitude:" + location.getLongitude());
        // textViewLatitude.setText("Latitude:" + location.getLatitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }





    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {


        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CODE: {
                // When request is cancelled, the results array are empty
                if (
                        (grantResults.length > 0) &&
                                (grantResults[0]
                                        + grantResults[1]
                                        + grantResults[2]
                                        == PackageManager.PERMISSION_GRANTED
                                )
                        ) {
                    // Permissions are granted
                   // Toast.makeText(SignInActivity.this, "Permissions granted.", Toast.LENGTH_SHORT).show();
                  //  getLocation();
                } else {
                    // Permissions are denied
                    //Toast.makeText(SignInActivity.this, "Permissions denied.", Toast.LENGTH_SHORT).show();
                }
                return;


            }
        }
    }






}