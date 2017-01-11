package com.mikhail.pankratov.improveandroidtest.remind;

import android.content.Context;

/**
 * Created by User on 11.01.2017.
 */

public interface RemindPresenter{

    void onAttacheView(RemindPasswordView view);

    void onDetachView();

    void onRemind(String email, Context context);
}
