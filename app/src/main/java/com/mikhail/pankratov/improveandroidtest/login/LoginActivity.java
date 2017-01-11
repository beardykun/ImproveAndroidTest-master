package com.mikhail.pankratov.improveandroidtest.login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mikhail.pankratov.improveandroidtest.R;
import com.mikhail.pankratov.improveandroidtest.remind.RemindActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private static final int DIALOG_CALL = 1;
    private static final int ERROR_SHOW_TIMEOUT = 2000;

    @BindView(R.id.full_name_edit)
    TextInputEditText nameEdit;
    @BindView(R.id.birth_date_edit)
    TextInputEditText dateEdit;
    @BindView(R.id.email_edit)
    TextInputEditText emailEdit;
    @BindView(R.id.username_edit)
    TextInputEditText usernameEdit;
    @BindView(R.id.password_edit)
    TextInputEditText passwordEdit;

    @BindView(R.id.full_name_text_input)
    TextInputLayout nameInputLayout;
    @BindView(R.id.birth_date_text_input)
    TextInputLayout dateInputLayout;
    @BindView(R.id.email_text_input)
    TextInputLayout emailInputLayout;
    @BindView(R.id.username_text_input)
    TextInputLayout usernameInputLayout;
    @BindView(R.id.password_text_input)
    TextInputLayout passwordInputLayout;

    @BindView(R.id.send_FAB)
    FloatingActionButton sendFAB;
    @BindView(R.id.remind_FAB)
    FloatingActionButton remindFAB;
    @BindView(R.id.layProgress)
    RelativeLayout progressLay;

    private int year, month, day;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(LoginActivity.this);

        presenter = new LoginPresenterImpl();

        setAnimationForFields();
        setCalendarDate();
    }

    private void setCalendarDate() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onAttacheView(this);
    }

    @Override
    protected void onStop() {
        presenter.onDetachView();
        super.onStop();
    }

    private void setAnimationForFields() {
        Animation fromLeft = AnimationUtils.loadAnimation(this, R.anim.from_left);
        Animation fromRight = AnimationUtils.loadAnimation(this, R.anim.from_right);

        nameEdit.startAnimation(fromLeft);
        dateEdit.startAnimation(fromRight);
        emailEdit.startAnimation(fromLeft);
        usernameEdit.startAnimation(fromRight);
        passwordEdit.startAnimation(fromLeft);
        sendFAB.setAnimation(fromRight);
        remindFAB.setAnimation(fromLeft);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_CALL) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            showDate(year, month + 1, day);
        }
    };

    private void showDate(int year, int month, int day) {
        dateEdit.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    @OnClick(R.id.send_FAB)
    public void onLoginClick() {
        presenter.login(nameEdit.getText().toString(), dateEdit.getText().toString(), emailEdit.getText().toString(),
                usernameEdit.getText().toString(), passwordEdit.getText().toString());
    }

    @OnClick(R.id.birth_date_edit)
    public void doBirthEdit() {
        showDialog(DIALOG_CALL);
    }

    @OnClick(R.id.remind_FAB)
    public void remindPassword() {
        Intent intent = new Intent(this, RemindActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        progressLay.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressLay.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFullNameValidationError() {
        showValidationError(nameInputLayout, getString(R.string.name_error));
    }

    @Override
    public void showBirthDateValidationError() {
        showValidationError(dateInputLayout, getString(R.string.date_error));
    }

    @Override
    public void showEmailValidationError() {
        showValidationError(emailInputLayout, getString(R.string.email_error));
    }

    @Override
    public void showUsernameValidationError() {
        showValidationError(usernameInputLayout, getString(R.string.username_error));
    }

    @Override
    public void showPasswordValidationError() {
        showValidationError(passwordInputLayout, getString(R.string.password_error));
    }

    public void showValidationError(final TextInputLayout layout, String error) {
        layout.setErrorEnabled(true);
        layout.setError(error);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setErrorEnabled(false);
                layout.setError("");
            }
        }, ERROR_SHOW_TIMEOUT);
    }

    @Override
    public void onLoginSuccess(Intent intent) {
        startActivity(intent);
    }

}
