

package esec.recycler.com.recyclercustomer.ui.profileedit;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;

import javax.inject.Inject;

import esec.recycler.com.recyclercustomer.data.DataManager;
import esec.recycler.com.recyclercustomer.data.network.model.ProfileUpdateModel;
import esec.recycler.com.recyclercustomer.ui.base.BasePresenter;
import esec.recycler.com.recyclercustomer.utils.rx.SchedulerProvider;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class ProfileEditPresenter<V extends  ProfileEditMvpView> extends BasePresenter<V>
        implements  ProfileEditMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";

    @Inject
    public ProfileEditPresenter(DataManager dataManager,
                                SchedulerProvider schedulerProvider,
                                CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onServerProfileUpdateClick(MultipartBody.Part fileToUpload, RequestBody name, RequestBody zip, RequestBody customer_id ){
        //validate email and password

        getMvpView().showLoading();




        // String weatherFromWhere = from_where.trim();
        // if (weatherFromWhere.isEmpty()) return;
        getCompositeDisposable().add(getDataManager().uploadFile(fileToUpload,name,zip,customer_id)

                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ProfileUpdateModel>() {
                    @Override
                    public void accept(@NonNull ProfileUpdateModel ProfileUpdateResponse)
                            throws Exception {

                        System.out.println("EEEEEEEEEEEEE"+new Gson().toJson(ProfileUpdateResponse.getMessage()));
                        Log.e("Success", new Gson().toJson(ProfileUpdateResponse.getMessage()));

                        if (ProfileUpdateResponse != null && ProfileUpdateResponse.getSuccess() ==1) {



                            getMvpView().alertDialogSingle(ProfileUpdateResponse.getMessage());

                           // getDataManager().setCustomerName(loginResponse.getCustomerName());
                            //getDataManager().setCustomer_Id(loginResponse.getCustomerId());
                            //getDataManager().setCustomerEmail(loginResponse.getEmail());
                           // getDataManager().setZip(loginResponse.getZip());
                         //   getDataManager().setMobile(loginResponse.getMobile());
                            //getDataManager().setRewardPoint(""+loginResponse.getRewardPoint());



                          //  getMvpView().openHomeActivity();
                            getMvpView().hideLoading();
                        }else{



                            getMvpView().alertDialogSingle(ProfileUpdateResponse.getMessage());
                        }



                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));


    }


    @Override
    public void onServerProfileUpdateWithoutImageClick(RequestBody name, RequestBody zip, RequestBody customer_id) {
        getMvpView().showLoading();




        // String weatherFromWhere = from_where.trim();
        // if (weatherFromWhere.isEmpty()) return;
        getCompositeDisposable().add(getDataManager().uploadFile1(name,zip,customer_id)

                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ProfileUpdateModel>() {
                    @Override
                    public void accept(@NonNull ProfileUpdateModel ProfileUpdateResponse)
                            throws Exception {

                        System.out.println("EEEEEEEEEEEEE1"+new Gson().toJson(ProfileUpdateResponse.getMessage()));
                        Log.e("Success", new Gson().toJson(ProfileUpdateResponse.getMessage()));

                        if (ProfileUpdateResponse != null && ProfileUpdateResponse.getSuccess() ==1) {



                            getMvpView().alertDialogSingle(ProfileUpdateResponse.getMessage());

                            // getDataManager().setCustomerName(loginResponse.getCustomerName());
                            //getDataManager().setCustomer_Id(loginResponse.getCustomerId());
                            //getDataManager().setCustomerEmail(loginResponse.getEmail());
                            // getDataManager().setZip(loginResponse.getZip());
                            //   getDataManager().setMobile(loginResponse.getMobile());
                            //getDataManager().setRewardPoint(""+loginResponse.getRewardPoint());



                            //  getMvpView().openHomeActivity();
                            getMvpView().hideLoading();
                        }else{



                            getMvpView().alertDialogSingle(ProfileUpdateResponse.getMessage());
                        }



                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void onViewPrepared() {
        getMvpView().onProfileValues(getDataManager().getCustomerName(),getDataManager().getCoustomerEmail(),getDataManager().getMobile(),getDataManager().getZip(),getDataManager().getCustomer_Id());

    }
}
