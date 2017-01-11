package com.mikhail.pankratov.improveandroidtest.remind;


/**
 * Created by User on 11.01.2017.
 */

public class RemindInteractorImpl implements RemindInteractor {

    @Override
    public void onRemind(String email, onRemindListener listener) {
        if (validate(email, listener)) {
            listener.onRemindSuccess();
        }
    }

    private boolean validate(String email, RemindInteractor.onRemindListener listener) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            listener.onEmailInputError();
            return false;
        }
        return true;
    }
}
