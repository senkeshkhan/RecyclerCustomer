package esec.recycler.com.recyclercustomer.remote;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

public class CommonClass {

    public static final int NO_ICON = -1;  //No icon

    public static final int RequestPermissionCode = 1;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;


    /** CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT */
    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet
            //Toast.makeText(context, activeNetworkInfo.getTypeName(), Toast.LENGTH_SHORT).show();

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }





    private void displayLocationSettingsRequest(final Activity context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i("TAG", "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("TAG", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(context, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i("TAG", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("TAG", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }




    protected boolean isLocationEnabled( Context context){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){
            /*
                Settings.Secure
                    Secure system settings, containing system preferences that applications can read
                    but are not allowed to write. These are for preferences that the user must
                    explicitly modify through the system UI or specialized APIs for those values,
                    not modified directly by applications.
            */
            /*
                public static final String LOCATION_MODE
                    The degree of location access enabled by the user.

                    When used with putInt(ContentResolver, String, int), must be one of
                    LOCATION_MODE_HIGH_ACCURACY, LOCATION_MODE_SENSORS_ONLY,
                    LOCATION_MODE_BATTERY_SAVING, or LOCATION_MODE_OFF. When used with
                    getInt(ContentResolver, String), the caller must gracefully handle additional
                    location modes that might be added in the future.

                    Note: do not rely on this value being present in settings.db or on
                    ContentObserver notifications for the corresponding Uri.
                    Use MODE_CHANGED_ACTION to receive changes in this value.

                    Constant Value: "location_mode"
            */
            /*
                public static int getInt (ContentResolver cr, String name, int def)
                    Convenience function for retrieving a single secure settings value as an integer.
                    Note that internally setting values are always stored as strings; this function
                    converts the string to an integer for you. The default value will be returned
                    if the setting is not defined or not an integer.

                Parameters
                    cr : The ContentResolver to access.
                    name : The name of the setting to retrieve.
                    def : Value to return if the setting is not defined.
                Returns
                    The setting's current value, or 'def' if it is not defined or not a valid integer.
            */
            // check location state for api version 19 or greater
            int locationMode = Settings.Secure.getInt(
                    context.getContentResolver(),
                    Settings.Secure.LOCATION_MODE,
                    0
            );

            /*
                public static final int LOCATION_MODE_OFF
                    Location access disabled.

                Constant Value: 0 (0x00000000)
            */
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        }else{
            /*
                public static String getString (ContentResolver resolver, String name)
                    Look up a name in the database.

                Parameters
                    resolver : to access the database with
                    name : to look up in the table
                Returns
                    the corresponding value, or null if not present
            */
            /*
                public static final String LOCATION_PROVIDERS_ALLOWED
                    This constant was deprecated in API level 19.
                    use LOCATION_MODE and MODE_CHANGED_ACTION (or PROVIDERS_CHANGED_ACTION)

                    Comma-separated list of location providers that activities may access.
                    Do not rely on this value being present in settings.db or on ContentObserver
                    notifications on the corresponding Uri.

                    Constant Value: "location_providers_allowed"
            */
            String locationProviders = Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.LOCATION_PROVIDERS_ALLOWED
            );

            /*
                public static boolean isEmpty (CharSequence str)
                    Returns true if the string is null or 0-length.

                Parameters
                    str : the string to be examined
                Returns
                    true : if str is null or zero length
            */
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    // Custom method to check GPS service is enabled or disabled
    protected boolean isGPSEnabled(Context context){
        /*
            LocationManager
                This class provides access to the system location services. These services allow
                applications to obtain periodic updates of the device's geographical location, or
                to fire an application-specified Intent when the device enters the proximity of
                a given geographical location.

                You do not instantiate this class directly; instead, retrieve it through
                Context.getSystemService(Context.LOCATION_SERVICE).
        */
        /*
            public abstract Object getSystemService (String name)
                Return the handle to a system-level service by name. The class of the returned
                object varies by the requested name.
        */
        /*
            public static final String LOCATION_SERVICE
                Use with getSystemService(Class) to retrieve a LocationManager for
                controlling location updates.

                Constant Value: "location"
        */
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        /*
            public boolean isProviderEnabled (String provider)
                Returns the current enabled/disabled status of the given provider.

                If the user has enabled this provider in the Settings menu, true is returned
                otherwise false is returned

                Callers should instead use LOCATION_MODE unless they depend on provider-specific
                APIs such as requestLocationUpdates(String, long, float, LocationListener).

                Before API version LOLLIPOP, this method would throw SecurityException if the
                location permissions were not sufficient to use the specified provider.

            Parameters
                provider : the name of the provider
            Returns
                true : if the provider exists and is enabled
            Throws
                IllegalArgumentException : if provider is null

        */
        boolean GPSStatus = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return GPSStatus;
    }

    // Custom method for api level 19 or higher to check location service status
    protected boolean isLocationEnabledFromAPI19(Context context){
        int locationMode = Settings.Secure.getInt(
                context.getContentResolver(),
                Settings.Secure.LOCATION_MODE,
                0
        );

        return locationMode != Settings.Secure.LOCATION_MODE_OFF;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    public void showToast(Context context,String values) {
        Toast.makeText(context, values, Toast.LENGTH_SHORT).show();
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

    protected void checkPermission(final Activity context){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission(
                context,Manifest.permission.ACCESS_FINE_LOCATION)
                + ContextCompat.checkSelfPermission(
                context,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            // Do something, when permissions not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    context,Manifest.permission.CAMERA)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    context,Manifest.permission.ACCESS_FINE_LOCATION)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    context,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                // If we should give explanation of requested permissions

                // Show an alert dialog here with request explanation
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Camera, Read Contacts and Write External" +
                        " Storage permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                context,
                                new String[]{
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                },
                                MY_PERMISSIONS_REQUEST_CODE
                        );
                    }
                });
                builder.setNeutralButton("Cancel",null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                        context,
                        new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        }else {
            // Do something, when permissions are already granted
            //  Toast.makeText(SignInActivity.this,"Permissions already granted",Toast.LENGTH_SHORT).show();
        }
    }

}
