package com.mikhail.pankratov.improveandroidtest.remind;


/**
 * Created by User on 11.01.2017.
 */

public interface RemindInteractor {

    interface onRemindListener {
        void onEmailInputError();

        void onRemindSuccess();
    }

    void onRemind(String email, onRemindListener listener);
}
