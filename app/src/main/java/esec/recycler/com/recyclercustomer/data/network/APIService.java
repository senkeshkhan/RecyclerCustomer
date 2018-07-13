package esec.recycler.com.recyclercustomer.data.network;

import android.content.Context;


import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;


import esec.recycler.com.recyclercustomer.BuildConfig;
import esec.recycler.com.recyclercustomer.data.network.model.BlogResponse;
import esec.recycler.com.recyclercustomer.data.network.model.ForgetPasswordModel;
import esec.recycler.com.recyclercustomer.data.network.model.LoginResponseModel;
import esec.recycler.com.recyclercustomer.data.network.model.SingUpResponseModel;
import esec.recycler.com.recyclercustomer.data.network.model.ProfileUpdateModel;
import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface APIService {

    String ENDPOINT = "http://api.openweathermap.org/";
    String API_KEY = "aa9af8d39d6519b1d47dec305bd253a4";
    String BASE_URL = "http://testedserver.com/eseco/public/";

    @GET("v2/5926ce9d11000096006ccb30")
    Observable<BlogResponse> getForecastForCity();


    @POST("api/apilogincustomer")
    @FormUrlEncoded
    Observable<LoginResponseModel> sendSignInPost(@Field("email") String email,
                                            @Field("password") String password);
    @POST("api/apisignupcustomer")
    @FormUrlEncoded
    Observable<SingUpResponseModel> sendSignUp(@Field("name") String name,
                                         @Field("email") String email,
                                         @Field("mobile") String mobile,
                                         @Field("password") String password,
                                         @Field("zip") String zip,
                                         @Field("user_type") String user_type,
                                               @Field("address") String address,
                                         @Field("age") String age,
                                         @Field("gender") String gender);





    @POST("api/apiforgotpassword")
    @FormUrlEncoded
    Observable<ForgetPasswordModel> sendForgetPassword(@Field("email_id") String email,
                                                 @Field("mobile") String mobile);



    @Multipart
    @POST("api/editcustomerdetails")
    Observable<ProfileUpdateModel> uploadFile(
            @Part MultipartBody.Part file,
            @Part("name") RequestBody name,
            @Part("zip") RequestBody zip,
            @Part("customer_id") RequestBody customer_id);

    @Multipart
    @POST("api/editcustomerdetails")
    Observable<ProfileUpdateModel> uploadFile1(

            @Part("name") RequestBody name,
            @Part("zip") RequestBody zip,
            @Part("customer_id") RequestBody customer_id);






    class Factory {


        public static APIService create(Context context) {

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);



            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                builder.addInterceptor(interceptor);
            }



            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);
            builder.cache(cache);

           // builder.addInterceptor(new UnauthorisedInterceptor(context));
            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(APIService.class);
        }
    }
}