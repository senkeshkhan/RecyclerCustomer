/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package esec.recycler.com.recyclercustomer.data.prefs;


import esec.recycler.com.recyclercustomer.data.DataManager;

/**
 * Created by janisharali on 27/01/17.
 */

public interface PreferencesHelper {


    void setCustomerName(String customerName);


    String getCustomerName();

    void setCustomer_Id(String customer_id);

    String getCustomer_Id();

    void setCustomerEmail(String customerEmail);

    String getCoustomerEmail();

    void setMobile(String mobile);

    String getMobile();

    void setZip(String zip);

    String getZip();


    void setRewardPoint(String rewardPoint);

    String getRewardPoint();


}
