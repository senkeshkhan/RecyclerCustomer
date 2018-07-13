

package esec.recycler.com.recyclercustomer.ui.signup;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;

import javax.inject.Inject;

import esec.recycler.com.recyclercustomer.data.DataManager;
import esec.recycler.com.recyclercustomer.data.network.model.SingUpResponseModel;
import esec.recycler.com.recyclercustomer.ui.base.BasePresenter;
import esec.recycler.com.recyclercustomer.utils.rx.SchedulerProvider;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


public class SignUpPresenter<V extends SignUpMvpView> extends BasePresenter<V>
        implements SignUpMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";

    @Inject
    public SignUpPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    /*void onServerSignUpClick(String name ,String email, String password,String confirm_password,String mobile,String zip,String address,String age,String gender, String customer);*/

    @Override
    public void onServerSignUpClick(String name,String email, String mobile,String password,String zip,String address,String age,String gender, String customer) {

        getMvpView().showLoading();




        // String weatherFromWhere = from_where.trim();
        // if (weatherFromWhere.isEmpty()) return;
        getCompositeDisposable().add(getDataManager().sendSignUp(name,email,mobile,password,zip,customer,address,age,gender).subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<SingUpResponseModel>() {
                    @Override
                    public void accept(@NonNull SingUpResponseModel signUpResponse)
                            throws Exception {
                        Log.e("Success", new Gson().toJson(signUpResponse.getMessage()));

                        if (signUpResponse != null && signUpResponse.getSuccess() ==1) {





                            getMvpView().alertDialogRegister(signUpResponse.getMessage());

                            getMvpView().hideLoading();
                        }else{



                            getMvpView().alertDialogSingle(signUpResponse.getMessage());
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
    public void onAlreadyAccountClick() {

        getMvpView().openSignInActivity();
    }
}
