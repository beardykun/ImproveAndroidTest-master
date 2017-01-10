package com.mikhail.pankratov.improveandroidtest;



/**
 * Created by User on 09.01.2017.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLoginListener {

    LoginInteractor interactor;
    LoginView view;

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
            view.onLoginSuccess();
        }
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
