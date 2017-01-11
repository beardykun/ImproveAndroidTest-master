package com.mikhail.pankratov.improveandroidtest.remind;

import android.content.Intent;

/**
 * Created by User on 11.01.2017.
 */

public interface RemindPasswordView {

    void showEmailInputError();

    void onRemindSuccess(Intent intent);
}
