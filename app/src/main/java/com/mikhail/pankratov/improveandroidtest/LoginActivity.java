package com.mikhail.pankratov.improveandroidtest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private static final int DIALOG_CALL = 1;
    private static final int ERROR_SHOW_TIMEOUT = 2000;

    @BindView(R.id.edit_full_name) TextInputEditText editName;
    @BindView(R.id.edit_birth_date) TextInputEditText editDate;
    @BindView(R.id.edit_email) TextInputEditText editEmail;
    @BindView(R.id.edit_username) TextInputEditText editUsername;
    @BindView(R.id.edit_password) TextInputEditText editPassword;

    @BindView(R.id.layout_edit_full_name) TextInputLayout layoutEditName;
    @BindView(R.id.layout_edit_birth_date)TextInputLayout layoutEditDate;
    @BindView(R.id.layout_edit_email)TextInputLayout layoutEditEmail;
    @BindView(R.id.layout_edit_username)TextInputLayout layoutEditUsername;
    @BindView(R.id.layout_edit_password)TextInputLayout layoutEditPassword;

    @BindView(R.id.send_button) ImageButton sendMail;
    @BindView(R.id.fine_choice) TextView fineChoice;
    @BindView(R.id.layProgress) RelativeLayout progressLay;

    private int year, month, day;

    LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(LoginActivity.this);

        presenter = new LoginPresenterImpl();

        fineChoice.setText("Fine choice!\nYou're gonna love our app!");
        setAnimationForFields();
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

    private void setAnimationForFields(){
        Animation fromLeft = AnimationUtils.loadAnimation(this, R.anim.from_left);
        Animation fromRight = AnimationUtils.loadAnimation(this, R.anim.from_right);

        editName.startAnimation(fromLeft);
        editDate.startAnimation(fromRight);
        editEmail.startAnimation(fromLeft);
        editUsername.startAnimation(fromRight);
        editPassword.startAnimation(fromLeft);
        sendMail.setAnimation(fromRight);
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
        editDate.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    @OnClick(R.id.send_button)
    public void onLoginClick() {
        presenter.login(editName.getText().toString(), editDate.getText().toString(), editEmail.getText().toString(),
        editUsername.getText().toString(), editPassword.getText().toString());
    }

    @OnClick(R.id.edit_birth_date)
    public void doBirthEdit(){
        editDate.setError(null);
        showDialog(DIALOG_CALL);
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
        layoutEditName.setErrorEnabled(true);
        layoutEditName.setError("Enter FullName, at least 3 letters");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutEditName.setErrorEnabled(false);
                layoutEditName.setError("");
            }
        }, ERROR_SHOW_TIMEOUT);
    }

    @Override
    public void showBirthDateValidationError() {
        layoutEditDate.setErrorEnabled(true);
        layoutEditDate.setError("Choose birth date");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutEditDate.setErrorEnabled(false);
                layoutEditDate.setError("");
            }
        }, ERROR_SHOW_TIMEOUT);
    }

    @Override
    public void showEmailValidationError() {
        layoutEditEmail.setErrorEnabled(true);
        layoutEditEmail.setError("Invalid email address");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutEditEmail.setErrorEnabled(false);
                layoutEditEmail.setError("");
            }
        }, ERROR_SHOW_TIMEOUT);
    }

    @Override
    public void showUsernameValidationError() {
        layoutEditUsername.setErrorEnabled(true);
        layoutEditUsername.setError("Enter username, at least 3 letters");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutEditUsername.setErrorEnabled(false);
                layoutEditUsername.setError("");
            }
        }, ERROR_SHOW_TIMEOUT);
    }

    @Override
    public void showPasswordValidationError() {
        layoutEditPassword.setErrorEnabled(true);
        layoutEditPassword.setError("Enter password, at least 6 letters");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutEditPassword.setErrorEnabled(false);
                layoutEditPassword.setError("");
            }
        }, ERROR_SHOW_TIMEOUT);
    }

    @Override
    public void onLoginSuccess(Intent intent) {
                startActivity(intent);
    }
}
