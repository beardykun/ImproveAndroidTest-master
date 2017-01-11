package com.mikhail.pankratov.improveandroidtest.remind;

import android.content.Context;
import android.content.Intent;
import com.mikhail.pankratov.improveandroidtest.login.LoginActivity;

/**
 * Created by User on 11.01.2017.
 */

public class RemindPresenterImpl implements RemindPresenter, RemindInteractor.onRemindListener {

    public RemindPresenterImpl(){interactor = new RemindInteractorImpl();}
    RemindInteractor interactor;
    RemindPasswordView view;
    @Override
    public void onEmailInputError() {
        if(view != null){
            view.showEmailInputError();
        }
    }

    @Override
    public void toLoginActivity(Context context) {
        view.onRemindSuccess(new Intent(context, LoginActivity.class));
    }

    @Override
    public void onRemindSuccess(Context context) {
                toLoginActivity(context);
    }

    @Override
    public void onAttacheView(RemindPasswordView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        this.view = null;
    }

    @Override
    public void onRemind(String email, Context context) {
        interactor.onRemind(email, this, context);
    }
}
