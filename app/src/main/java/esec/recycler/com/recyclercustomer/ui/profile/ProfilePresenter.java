

package esec.recycler.com.recyclercustomer.ui.profile;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;

import javax.inject.Inject;

import esec.recycler.com.recyclercustomer.R;
import esec.recycler.com.recyclercustomer.data.DataManager;
import esec.recycler.com.recyclercustomer.data.network.model.LoginResponseModel;
import esec.recycler.com.recyclercustomer.ui.base.BasePresenter;
import esec.recycler.com.recyclercustomer.ui.signin.SignInMvpPresenter;
import esec.recycler.com.recyclercustomer.ui.signin.SignInMvpView;
import esec.recycler.com.recyclercustomer.utils.CommonUtils;
import esec.recycler.com.recyclercustomer.utils.rx.SchedulerProvider;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


public class ProfilePresenter<V extends ProfileMvpView> extends BasePresenter<V>
        implements ProfileMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";

    @Inject
    public ProfilePresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {

        getMvpView().onProfileValues(getDataManager().getCustomerName(),getDataManager().getCoustomerEmail(),getDataManager().getMobile(),getDataManager().getZip());



    }
}
