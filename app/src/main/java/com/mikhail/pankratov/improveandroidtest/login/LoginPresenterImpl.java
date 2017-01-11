package com.mikhail.pankratov.improveandroidtest.login;


import android.content.Intent;

/**
 * Created by User on 09.01.2017.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLoginListener {


    LoginInteractor interactor;
    LoginView view;

    private String fullName;
    private String birthDate;
    private String email;
    private String username;
    private String password;

    public LoginPresenterImpl() {
        interactor = new LoginInteractorImpl();
    }

    @Override
    public void onFullNameValidationFailed() {
        if (view != null) {
            view.hideProgress();
            view.showFullNameValidationError();
        }
    }

    @Override
    public void onBirthDateValidationFailed() {
        if (view != null) {
            view.hideProgress();
            view.showBirthDateValidationError();
        }
    }

    @Override
    public void onEmailValidationFailed() {
        if (view != null) {
            view.hideProgress();
            view.showEmailValidationError();
        }
    }

    @Override
    public void onUsernameValidationFailed() {
        if (view != null) {
            view.hideProgress();
            view.showUsernameValidationError();
        }
    }

    @Override
    public void onPasswordValidationFailed() {
        if (view != null) {
            view.hideProgress();
            view.showPasswordValidationError();
        }
    }

    @Override
    public void onError(String error) {
        if (view != null) {
            view.hideProgress();
            view.showError(error);
        }
    }

    @Override
    public void onSuccess() {
        if (view != null) {
            view.hideProgress();
            view.onLoginSuccess(sendMessage());

        }
    }

    @Override
    public Intent sendMessage() {
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

        return intentToSend;
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
            this.fullName = fullName;
            this.birthDate = birthDate;
            this.email = email;
            this.username = username;
            this.password = password;
            interactor.login(fullName, birthDate, email, username, password, this);
        }
    }
}
