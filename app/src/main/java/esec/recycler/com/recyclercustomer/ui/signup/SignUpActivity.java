package esec.recycler.com.recyclercustomer.ui.signup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.hbb20.CountryCodePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import esec.recycler.com.recyclercustomer.R;
import esec.recycler.com.recyclercustomer.remote.CommonClass;
import esec.recycler.com.recyclercustomer.ui.base.BaseActivity;
import esec.recycler.com.recyclercustomer.ui.signin.SignInActivity;


import javax.inject.Inject;

import static esec.recycler.com.recyclercustomer.remote.CommonClass.isValidEmail;


public class SignUpActivity extends BaseActivity implements SignUpMvpView {

    Context context;


    protected static final int REQUEST_CHECK_SETTINGS = 0x1;

    String str_gender ="male";

    Dialog dialog;

    @BindView(R.id.ed_email)
    EditText mEmailEditText;

    @BindView(R.id.ed_password)
    EditText mPasswordEditText;


    @BindView(R.id.ed_con_password)
    EditText mConfirmPasswordEditText;


    @BindView(R.id.ed_name)
    EditText mNameEditText;

    @BindView(R.id.ed_mobno)
    EditText mMobileEditText;

    @BindView(R.id.ed_zip)
    EditText mZipEditText;

    @BindView(R.id.ed_address)
    EditText mAddressEditText;

    @BindView(R.id.ed_age)
    EditText mAgeEditText;


    @BindView(R.id.ch_termsconditions)
    CheckBox mTermsConditionsCheckBox;

    @BindView(R.id.alreadylogin_button)
    Button getAlreadylogin_button;

    @BindView(R.id.ccp)
    CountryCodePicker countryCodePicker;
    //countryCodePicker = (CountryCodePicker) getView().findViewById(R.id.ccp);
    @Inject
    SignUpMvpPresenter<SignUpMvpView> mPresenter;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        return intent;
    }

    private static final String TAG = SignUpActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);





        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(SignUpActivity.this);





        System.out.println("XXXXXXXXXXXXXXXXXXx"+  countryCodePicker.getSelectedCountryCode());

        context = SignUpActivity.this;


        getAlreadylogin_button.setText(Html.fromHtml("Have an account already ? <font color='#1156C1'> Signin</font>"));


    }


    @OnClick({R.id.radio_male, R.id.radio_female})
    public void onRadioButtonClicked(RadioButton radioButton) {
        // Is the button now checked?
        boolean checked = radioButton.isChecked();

        // Check which radio button was clicked
        switch (radioButton.getId()) {
            case R.id.radio_male:
                if (checked) {


                    str_gender="male";

                }
                break;
            case R.id.radio_female:
                if (checked) {


                    str_gender="female";

                }
                break;
        }
    }



    @OnClick(R.id.alreadylogin_button)
    void  onAlreadyAccountClick(View v){
        mPresenter.onAlreadyAccountClick();
    }


    @OnClick(R.id.btn_signup)
    void onServerSignUpClick(View v) {
        System.out.println("XXXXXXXXXXXXXXXXXXx"+  countryCodePicker.getSelectedCountryCode());
        if (validate()) {

            mPresenter.onServerSignUpClick(mNameEditText.getText().toString(),mEmailEditText.getText().toString(),
                    mMobileEditText.getText().toString(),mPasswordEditText.getText().toString(),mZipEditText.getText().toString(),mAddressEditText.getText().toString(),mAgeEditText.getText().toString(),str_gender,"customer");

        } else {

        }

    }


        @Override
    protected void setUp() {

    }



    @Override
    public void openSignInActivity() {
        Intent i = SignInActivity.getStartIntent(SignUpActivity.this);
        startActivity(i);
        finish();

    }


    private boolean validate() {
        boolean temp=true;
        String checkemail = mEmailEditText.getText().toString();
        String pass=mPasswordEditText.getText().toString();
        String cpass=mConfirmPasswordEditText.getText().toString();
        String name = mNameEditText.getText().toString();
        String zip = mZipEditText.getText().toString();
        String mobno = mMobileEditText.getText().toString();
        String address = mAddressEditText.getText().toString();
        String age = mAgeEditText.getText().toString();


       //
        if(name.length()<1){

            mNameEditText.setError("Please enter your name");

            temp=false;
        }

        if(checkemail.length()<1){
            mEmailEditText.setError("Please enter email address");
            temp=false;
        }else if(!isValidEmail(checkemail)) {
            mEmailEditText.setError("Please enter a valid email id");
            temp = false;
        }

       if(pass.length()<1){
            mPasswordEditText.setError("please enter password");
            temp=false;
        } if(!pass.equals(cpass)){
            mConfirmPasswordEditText.setError("password not matching");

            temp=false;
        } if(cpass.length()<1){
            mConfirmPasswordEditText.setError("Please enter confirm password");
            temp=false;
        }if(mobno.length()<1){

            mMobileEditText.setError("Please enter mobile number");
        temp=false;
    } if(zip.length()<1){
            mZipEditText.setError("Please enter zipcode");

            temp=false;
        } if(address.length()<1){
            mAddressEditText.setError("Please enter your location");

            temp=false;
        } if(age.length()<1){
            mAgeEditText.setError("Please enter age");

            temp=false;
        }else if(!mTermsConditionsCheckBox.isChecked()){

            dialog = CommonClass.createMessageDialog(SignUpActivity.this, "", "Please check terms and conditions", "ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            },CommonClass.NO_ICON);
            dialog.show();







            temp=false;
        }else if(!isNetworkConnected()){


            alertDialogSingle("Network not available");



            temp=false;


        }

        return temp;
    }




    @Override
    public void onBackPressed() {
        Intent i = new Intent(SignUpActivity.this,SignInActivity.class);
        startActivity(i);
        finish();
    }



    }