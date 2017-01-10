package com.mikhail.pankratov.improveandroidtest;



/**
 * Created by User on 09.01.2017.
 */

public interface LoginInteractor {

    interface onLoginListener{

        void onFullNameValidationFailed();

        void onBirthDateValidationFailed();

        void onEmailValidationFailed();

        void onUsernameValidationFailed();

        void onPasswordValidationFailed();

        void onError(String error);

        void onSuccess();
    }

    void login(String fullName, String birthDate, String email,
               String username, String password, onLoginListener listener);
}
