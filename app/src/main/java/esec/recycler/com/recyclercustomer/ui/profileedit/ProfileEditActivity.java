package esec.recycler.com.recyclercustomer.ui.profileedit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import esec.recycler.com.recyclercustomer.R;
import esec.recycler.com.recyclercustomer.ui.base.BaseActivity;
import esec.recycler.com.recyclercustomer.utils.CommonUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileEditActivity extends BaseActivity implements ProfileEditMvpView {

    Context context;

    String mediaPath;
    String str_customerid;
    int i=0;

    @BindView(R.id.ed_name)
    EditText ed_name;

    @BindView(R.id.ed_email)
    EditText ed_email;

    @BindView(R.id.ed_mobno)
    EditText ed_mobno;

    @BindView(R.id.ed_zip)
    EditText ed_zip;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.img_profile)
    ImageView img_profile;

    MultipartBody.Part fileToUpload;

    @Inject
    ProfileEditMvpPresenter<ProfileEditMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ProfileEditActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);


        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(ProfileEditActivity.this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //checkPermission(ProfileEditActivity.this);
            requestPermissionsSafely(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        } else {
            // write your logic here
        }


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        context = ProfileEditActivity.this;

        setUp();



    }

    @OnClick(R.id.btn_Update)
    void onServerProfileUpdateClick(View v){





        System.out.println("11111111111111"+mediaPath+","+str_customerid+","+ed_name.getText().toString()+","+ed_zip.getText().toString());



        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), ed_name.getText().toString());
        RequestBody zip = RequestBody.create(MediaType.parse("text/plain"), ed_zip.getText().toString());
        RequestBody customer_id = RequestBody.create(MediaType.parse("text/plain"), str_customerid);



        if(validate()) {

            if (mediaPath != null) {

                File file = new File(mediaPath);

                System.out.println("222222222222222"+file.getName());

                // Parsing any Media type file
                RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);


                fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), requestBody);


                mPresenter.onServerProfileUpdateClick(fileToUpload,filename,zip,customer_id);
                //uploadFile();
            } else {
               mPresenter.onServerProfileUpdateWithoutImageClick(filename,zip,customer_id);


            }

        }

    }


    @OnClick(R.id.img_profile)
    void imageClick(View v){


        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 0);


        }
    @Override
    protected void setUp() {

        mPresenter.onViewPrepared();
    }


    @Override
    public void onProfileValues(String name, String email, String mobile, String zip,String customer_id) {

        ed_name.setText(name);
        ed_email.setText(email);
        ed_mobno.setText(mobile);
        ed_zip.setText(zip);
        str_customerid=customer_id;
    }



    private boolean validate() {
        boolean temp=true;
        String checkemail = ed_email.getText().toString();

        String name = ed_name.getText().toString();
        String zip = ed_zip.getText().toString();
        String mobno = ed_mobno.getText().toString();



        //
        if(name.length()<1){
            // Toast.makeText(SignUpActivity.this,"Invalid Email Address",Toast.LENGTH_SHORT).show();
            ed_name.setError("please enter the your name");

            temp=false;
        }


        if(!CommonUtils.isEmailValid(checkemail)){

            ed_email.setError("Invalid Email Address");
            // Toast.makeText(SignUpActivity.this,"Password Not matching",Toast.LENGTH_SHORT).show();
            temp=false;
        } if(mobno.length()<1){

            ed_mobno.setError("please enter the your mobilenumber");
            temp=false;
        } if(zip.length()<1){
            ed_zip.setError("please enter the zipcode");

            temp=false;
        }
        /*if(mediaPath==null){


            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEditActivity.this);
            builder.setCancelable(false);
            builder.setMessage("please select the your image");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application dialog.cancel();


                    dialog.dismiss();

                }
            });

            AlertDialog alert = builder.create();
            alert.show();
            //Toast.makeText(getApplicationContext(),"please select the your image",Toast.LENGTH_SHORT).show();

            temp=false;
        }*/

        return temp;
    }














    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                // Set the Image in ImageView for Previewing the Media
                img_profile.setImageBitmap(BitmapFactory.decodeFile(mediaPath));

                System.out.println("iiiiiiiiiiiiii"+mediaPath);
                cursor.close();

            } else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

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