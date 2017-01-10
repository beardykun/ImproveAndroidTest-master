package com.mikhail.pankratov.improveandroidtest;


import android.content.Intent;

/**
 * Created by User on 09.01.2017.
 */

public interface LoginPresenter {

    void onAttacheView(LoginView view);

    void onDetachView();

    void login(String fullName, String birthDate, String email,
               String username, String password);


}
