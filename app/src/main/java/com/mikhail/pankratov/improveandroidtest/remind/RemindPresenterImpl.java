package com.mikhail.pankratov.improveandroidtest.remind;


/**
 * Created by User on 11.01.2017.
 */

public class RemindPresenterImpl implements RemindPresenter, RemindInteractor.onRemindListener {

    RemindInteractor interactor;
    RemindView view;

    public RemindPresenterImpl() {
        interactor = new RemindInteractorImpl();
    }

    @Override
    public void onEmailInputError() {
        if (view != null) {
            view.showEmailInputError();
        }
    }

    @Override
    public void toLoginActivity() {
        view.onRemindSuccess();
    }

    @Override
    public void onRemindSuccess() {
        toLoginActivity();
    }

    @Override
    public void onAttacheView(RemindView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        this.view = null;
    }

    @Override
    public void onRemind(String email) {
        interactor.onRemind(email, this);
    }

}
