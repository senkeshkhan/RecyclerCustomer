package esec.recycler.com.recyclercustomer.ui.forgetpassword;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import esec.recycler.com.recyclercustomer.R;
import esec.recycler.com.recyclercustomer.remote.APIService;
import esec.recycler.com.recyclercustomer.remote.CommonClass;
import esec.recycler.com.recyclercustomer.ui.base.BaseActivity;

import esec.recycler.com.recyclercustomer.ui.signin.SignInActivity;

import static esec.recycler.com.recyclercustomer.utils.CommonUtils.isValidEmail;
import static esec.recycler.com.recyclercustomer.utils.CommonUtils.isValidPhoneNumber;

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordMvpView  {


    EditText ed_email,ed_mobile;
    Button btn_forget;
    private APIService mAPIService;

    String str_email,str_phone;

    Dialog dialog;
    @BindView(R.id.ed_email)
    EditText mEmailEditText;



    @Inject
    ForgetPasswordMvpPresenter<ForgetPasswordMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(ForgetPasswordActivity.this);





/*
        btn_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_email=mEmailEditText.getText().toString();
                str_phone=mMobileEditText.getText().toString();
                if(isValidEmail(str_email) ){



                    sendForgetPass(str_email,"");
                }else if(isValidPhoneNumber(str_email)){

                    sendForgetPass("",str_email);



                }else if(!CommonClass.checkConnection(ForgetPasswordActivity.this)){



                    dialog = CommonClass.createMessageDialog(ForgetPasswordActivity.this, "","Network not available",
                            "ok", new DialogInterface.OnClickListener()
                            {

                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    // TODO Auto-generated method stub



                                }
                            }, CommonClass.NO_ICON);
                    dialog.show();




                }else{
                    if(ed_email.length()<1){
                        ed_email.setError("Please enter email address or mobile number");
                    }else if(!isValidEmail(str_email)){
                        ed_email.setError("Please enter a valid email id");
                    }
                    if(str_phone.length()<1){
                        ed_mobile.setError("Please enter mobile number");
                    }
                }


            }
        });*/

    }







    @OnClick(R.id.btn_forget)
    void onServerForgetPasswordClick(View v) {
        str_email=mEmailEditText.getText().toString();

        if(isValidEmail(str_email) ){


            mPresenter.onServerForgetPasswordClick(str_email,"");

        }else if(isValidPhoneNumber(str_email)){


            mPresenter.onServerForgetPasswordClick("",str_email);


        }else if(!CommonClass.checkConnection(ForgetPasswordActivity.this)){



            alertDialogSingle("Network not available");





        }else{
            if(str_email.length()<1){
                mEmailEditText.setError("Please enter email address or mobile number");
            }else if(!isValidEmail(str_email)){
                mEmailEditText.setError("Please enter a valid email id");
            }

        }




    }
    @Override
    protected void setUp() {

    }

    @Override
    public void openSignInActivity() {

        Intent i = SignInActivity.getStartIntent(ForgetPasswordActivity.this);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = SignInActivity.getStartIntent(ForgetPasswordActivity.this);
        startActivity(i);
        finish();
    }
}
