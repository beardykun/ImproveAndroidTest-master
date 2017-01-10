package com.mikhail.pankratov.improveandroidtest;


import android.content.Intent;
import android.support.design.widget.TextInputLayout;

/**
 * Created by User on 09.01.2017.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLoginListener {

    LoginInteractor interactor;
    LoginView view;
    private Intent intent;

    public LoginPresenterImpl(){interactor = new LoginInteractorImpl();}

    @Override
    public void onFullNameValidationFailed() {
        if(view != null){
            view.hideProgress();
            view.showFullNameValidationError();
        }
    }

    @Override
    public void onBirthDateValidationFailed() {
        if(view != null){
            view.hideProgress();
            view.showBirthDateValidationError();
        }
    }

    @Override
    public void onEmailValidationFailed() {
        if(view != null){
            view.hideProgress();
            view.showEmailValidationError();
        }
    }

    @Override
    public void onUsernameValidationFailed() {
        if(view != null){
            view.hideProgress();
            view.showUsernameValidationError();
        }
    }

    @Override
    public void onPasswordValidationFailed() {
        if(view != null){
            view.hideProgress();
            view.showPasswordValidationError();
        }
    }

    @Override
    public void onError(String error) {
        if(view != null){
            view.hideProgress();
            view.showError(error);
        }
    }

    @Override
    public void onSuccess() {
        if(view != null){
            view.hideProgress();
            view.onLoginSuccess(intent);

        }
    }

    @Override
    public void sendMessage(String fullName, String birthDate, String email,
                            String username, String password) {
        String[] emailTo = new String[1];
        emailTo[0] = email;

        String toSend = "Full Name: " + fullName + "\nBirth Date: " + birthDate
                + "\nUsername: " + username + "\nPassword: "
                + password + "\nEmail: " + email;
        Intent intentToSend = new Intent(Intent.ACTION_SEND);
        intentToSend.putExtra(Intent.EXTRA_SUBJECT, "Registration.");
        intentToSend.putExtra(Intent.EXTRA_EMAIL, emailTo);
        intentToSend.setType("text/plain");
        intentToSend.putExtra(Intent.EXTRA_TEXT, toSend);
        intent = intentToSend;
    }

    @Override
    public void onAttacheView(LoginView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        view = null;
    }

    @Override
    public void login(String fullName, String birthDate, String email, String username, String password) {
        if (view != null) {
            view.showProgress();
            interactor.login(fullName, birthDate, email, username, password, this);
        }
    }
}
