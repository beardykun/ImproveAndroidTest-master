package com.mikhail.pankratov.improveandroidtest;


import android.content.Intent;

/**
 * Created by User on 09.01.2017.
 */

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(String fullName, String birthDate, String email,
                      String username, String password, onLoginListener listener) {
            if(validate(fullName, birthDate, email, username, password, listener)){
                listener.onSuccess();
            }
    }

    private boolean validate(String fullName, String birthDate, String email,
                             String username, String password, onLoginListener listener){
        if (fullName.length() < 3){
            listener.onFullNameValidationFailed();
            return false;
        } else if (birthDate.length() < 1){
            listener.onBirthDateValidationFailed();
            return false;
        }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            listener.onEmailValidationFailed();
            return false;
        }else if (username.length() < 3){
            listener.onUsernameValidationFailed();
            return false;
        }else if (password.length() < 6){
            listener.onPasswordValidationFailed();
            return false;
        }
        return true;
    }
}
