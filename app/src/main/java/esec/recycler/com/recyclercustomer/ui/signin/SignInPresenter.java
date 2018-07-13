

package esec.recycler.com.recyclercustomer.ui.signin;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;

import javax.inject.Inject;

import esec.recycler.com.recyclercustomer.R;
import esec.recycler.com.recyclercustomer.data.DataManager;
import esec.recycler.com.recyclercustomer.data.network.model.LoginResponseModel;
import esec.recycler.com.recyclercustomer.ui.base.BasePresenter;
import esec.recycler.com.recyclercustomer.utils.CommonUtils;
import esec.recycler.com.recyclercustomer.utils.rx.SchedulerProvider;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


public class SignInPresenter<V extends SignInMvpView> extends BasePresenter<V>
        implements SignInMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";

    @Inject
    public SignInPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onServerSigInClick(String email, String password) {
        //validate email and password
        if (email == null || email.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.invalid_email);
            return;
        }
        if (password == null || password.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }
        getMvpView().showLoading();




        // String weatherFromWhere = from_where.trim();
        // if (weatherFromWhere.isEmpty()) return;
        getCompositeDisposable().add(getDataManager().sendSignInPost(email,password)

                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LoginResponseModel>() {
                    @Override
                    public void accept(@NonNull LoginResponseModel loginResponse)
                            throws Exception {
                        Log.e("Success", new Gson().toJson(loginResponse.getMessage()));

                        if (loginResponse != null && loginResponse.getSuccess() ==1) {



                            getDataManager().setCustomerName(loginResponse.getCustomerName());
                            getDataManager().setCustomer_Id(loginResponse.getCustomerId());
                            getDataManager().setCustomerEmail(loginResponse.getEmail());
                            getDataManager().setZip(loginResponse.getZip());
                            getDataManager().setMobile(loginResponse.getMobile());
                            getDataManager().setRewardPoint(""+loginResponse.getRewardPoint());



                            getMvpView().openHomeActivity();
                            getMvpView().hideLoading();
                        }else{



                            getMvpView().alertDialogSingle(loginResponse.getMessage());
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
    public void onRegisterClick() {
        getMvpView().openRegisterActivity();
    }

    @Override
    public void onForgetPasswordClick() {

        getMvpView().openForgetPasswordActivity();

    }
}
