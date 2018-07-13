package esec.recycler.com.recyclercustomer.remote;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
	static SharedPreferences preferences;

	public static void setCustomerName(String customerName, Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		preferences.edit().putString("customerName", customerName).commit();
	}

	public static String getCustomerName(Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		return preferences.getString("customerName", "");
	}

	public static void setCustomer_Id(String customer_id, Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		preferences.edit().putString("customer_id", customer_id).commit();
	}

	public static String getCustomer_Id(Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		return preferences.getString("customer_id", "");
	}

	public static void setUseremail(String useremail, Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		preferences.edit().putString("user_email", useremail).commit();
	}

	public static String getUseremail(Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		return preferences.getString("user_email", "");
	}

	public static void setMobile(String mobile, Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		preferences.edit().putString("mobile", mobile).commit();
	}

	public static String getMobile(Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		return preferences.getString("mobile", "");
	}

	public static void setZip(String zip, Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		preferences.edit().putString("zip", zip).commit();
	}

	public static String getZip(Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		return preferences.getString("zip", "");
	}


	public static void setRewardPoint(String rewardPoint, Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		preferences.edit().putString("rewardPoint", rewardPoint).commit();
	}

	public static String getRewardPoint(Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		return preferences.getString("rewardPoint", "");
	}



	public static void setLatitude(String Latitude, Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		preferences.edit().putString("latitude", Latitude).commit();
	}

	public static String getLatitude(Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		return preferences.getString("latitude", "");
	}

	public static void setLongitude(String Longitude, Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		preferences.edit().putString("longitude", Longitude).commit();
	}

	public static String getLongitude(Context context) {

		preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
		return preferences.getString("longitude", "");
	}



}

