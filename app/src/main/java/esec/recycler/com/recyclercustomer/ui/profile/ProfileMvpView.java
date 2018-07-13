package esec.recycler.com.recyclercustomer.ui.profile;

import esec.recycler.com.recyclercustomer.ui.base.MvpView;


public interface ProfileMvpView extends MvpView {

    void onProfileValues(String name, String email,String mobile,String zip);

}