package com.mikhail.pankratov.improveandroidtest.remind;


/**
 * Created by User on 11.01.2017.
 */

public interface RemindPresenter {

    void onAttacheView(RemindView view);

    void onDetachView();

    void onRemind(String email);
}
