package esec.recycler.com.recyclercustomer.remote;



import esec.recycler.com.recyclercustomer.data.network.model.ForgetPasswordModel;
import esec.recycler.com.recyclercustomer.data.network.model.LoginResponseModel;
import esec.recycler.com.recyclercustomer.data.network.model.ProfileUpdateModel;
import esec.recycler.com.recyclercustomer.data.network.model.SingUpResponseModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Chike on 12/3/2016.
 */

public interface APIService {

    @POST("api/apisignupcustomer")
    @FormUrlEncoded
    Call<SingUpResponseModel> sendSignUp(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("mobile") String mobile,
                                       @Field("password") String password,
                                       @Field("zip") String zip,
                                       @Field("user_type") String user_type,

                                         @Field("address") String address,
                                         @Field("age") String age,
                                         @Field("gender") String gender);



  //  http://testedserver.com/eseco/public/api/apilogincustomer


    @POST("api/apilogincustomer")
    @FormUrlEncoded
    Call<LoginResponseModel> sendSignInPost(@Field("email") String email,
                                            @Field("password") String password);


    @POST("api/apiforgotpassword")
    @FormUrlEncoded
    Call<ForgetPasswordModel> sendForgetPassword(@Field("email_id") String email,
                                             @Field("mobile") String mobile);

    //sample@gmail.com

//1234
//12.3452
//30.4532


    @Multipart
    @POST("api/editcustomerdetails")
    Call<ProfileUpdateModel> profileUpdate(@Part MultipartBody.Part file,@Field("name") String name ,@Field("zip") String zip,@Field("customer_id") String customer_id);


    @Multipart
    @POST("api/editcustomerdetails")
    Call<ProfileUpdateModel> uploadFile(
            @Part MultipartBody.Part file,
            @Part("name") RequestBody name,
            @Part("zip") RequestBody zip,
            @Part("customer_id") RequestBody customer_id);

    @Multipart
    @POST("api/editcustomerdetails")
    Call<ProfileUpdateModel> uploadFile1(

            @Part("name") RequestBody name,
            @Part("zip") RequestBody zip,
            @Part("customer_id") RequestBody customer_id);

    @POST("api/apisignupcustomer")
    Call<SingUpResponseModel> getCityList(
            @Query("name") String name,
            @Query("email") String email,
            @Query("mobile") String mobile,
            @Query("password") String password,
            @Query("zip") String zip,
            @Query("user_type") String user_type,
            @Query("lat") String lat,
            @Query("long") String longs
    );







    // RxJava
   /* @POST("/posts")
    @FormUrlEncoded
    Observable<SingUpResponseModel> savePost(@Field("title") String title,
                              @Field("body") String body,
                              @Field("userId") long userId);*/

    @POST("/posts")
    Call<SingUpResponseModel> savePost(@Body SingUpResponseModel singUpResponseModel);

    @PUT("/posts/{id}")
    @FormUrlEncoded
    Call<SingUpResponseModel> updatePost(@Path("id") long id,
                                         @Field("title") String title,
                                         @Field("body") String body,
                                         @Field("userId") long userId);

    @DELETE("/posts/{id}")
    Call<SingUpResponseModel> deletePost(@Path("id") long id);

}
