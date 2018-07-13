package esec.recycler.com.recyclercustomer.ui.profileedit;

import esec.recycler.com.recyclercustomer.ui.base.MvpView;


public interface ProfileEditMvpView extends MvpView {

    void onProfileValues(String name, String email,String mobile,String zip,String customerid);
}