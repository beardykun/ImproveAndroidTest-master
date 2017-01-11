package com.mikhail.pankratov.improveandroidtest.remind;

import android.content.Context;

/**
 * Created by User on 11.01.2017.
 */

public interface RemindInteractor {

    interface onRemindListener {
        void onEmailInputError();

        void toLoginActivity(Context context);

        void onRemindSuccess(Context context);
    }

    void onRemind(String email, onRemindListener listener, Context context);
}
