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

package esec.recycler.com.recyclercustomer.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Patterns;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import esec.recycler.com.recyclercustomer.R;


/**
 * Created by janisharali on 27/01/17.
 */

public final class CommonUtils {

    private static final String TAG = "CommonUtils";
    public static final int NO_ICON = -1;  //No icon

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName)
            throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }




    public static Dialog createMessageDialog(Context context, String title, String message,
                                             String btnName, DialogInterface.OnClickListener listener, int iconId)
    {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON)
        {
            //The settings dialog icons
            builder.setIcon(iconId);
        }
        //Set the title of the dialog box
        builder.setTitle(title);
        //The settings dialog messages
        builder.setMessage(message);
        //Set the button
        builder.setPositiveButton(btnName, listener);
        //Create a message dialog box
        dialog = builder.create();

        return dialog;
    }



    public static boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
        return false;
    }



    public static boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    public static Dialog createConfirmDialog(Context context, String title, String message,
                                             String positiveBtnName, String negativeBtnName, DialogInterface.OnClickListener positiveBtnListener,
                                             DialogInterface.OnClickListener negativeBtnListener, int iconId)
    {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON)
        {
            //The settings dialog icons
            builder.setIcon(iconId);
        }
        //Set the title of the dialog box
        builder.setTitle(title);
        //The settings dialog messages
        builder.setMessage(message);
        //Set the OK button
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        //Set the Cancel button
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        //Create a message dialog box
        dialog = builder.create();

        return dialog;
    }
}
