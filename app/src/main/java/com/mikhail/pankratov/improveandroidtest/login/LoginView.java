package com.mikhail.pankratov.improveandroidtest.login;

import android.content.Intent;


/**
 * Created by User on 09.01.2017.
 */

public interface LoginView {

    void showProgress();

    void hideProgress();

    void showError(String error);

    void showFullNameValidationError();

    void showBirthDateValidationError();

    void showEmailValidationError();

    void showUsernameValidationError();

    void showPasswordValidationError();

    void onLoginSuccess(Intent intent);
}
