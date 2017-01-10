package com.mikhail.pankratov.improveandroidtest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Handler;
import android.preference.PreferenceActivity;
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

    @BindView(R.id.edit_full_name) EditText editName;

    @BindView(R.id.edit_birth_date) TextView editDate;

    @BindView(R.id.edit_email) EditText editEmail;

    @BindView(R.id.edit_username) EditText editUsername;

    @BindView(R.id.edit_password) EditText editPassword;

    @BindView(R.id.send_button) ImageButton sendMail;

    @BindView(R.id.fine_choice) TextView fineChoice;

    @BindView(R.id.layProgress) RelativeLayout progressLay;

    private TextView text;

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

    @OnClick({R.id.send_button, R.id.edit_birth_date})
    public void onLoginClick(View view) {

        switch (view.getId()){
            case R.id.send_button:
                presenter.login(editName.getText().toString(), editDate.getText().toString(), editEmail.getText().toString(),
                        editUsername.getText().toString(), editPassword.getText().toString());
                break;

            case R.id.edit_birth_date:
                editDate.setError(null);
                showDialog(DIALOG_CALL);
                break;
        }
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
        editName.setError("Enter FullName, at least 3 letters");
    }

    @Override
    public void showBirthDateValidationError() {
        editDate.setError("Choose birth date");
    }

    @Override
    public void showEmailValidationError() {
        editEmail.setError("Invalid email address");
    }

    @Override
    public void showUsernameValidationError() {
        editUsername.setError("Enter username, at least 3 letters");
    }

    @Override
    public void showPasswordValidationError() {
        editPassword.setError("Enter password, at least 6 letters");
    }

    @Override
    public void onLoginSuccess() {
                String[] emailTo = new String[1];
                emailTo[0] = editEmail.getText().toString();

                String toSend = "FullName: " + editName.getText().toString() + "\nBirth Date: " + editDate.getText()
                        + "\nUsername: " + editUsername.getText().toString() + "\nPassword: "
                        + editPassword.getText().toString() + "\nEmail: " + editEmail.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Registration.");
                intent.putExtra(Intent.EXTRA_EMAIL, emailTo);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, toSend);
                startActivity(intent);
    }

    @OnTextChanged({R.id.edit_full_name, R.id.edit_password, R.id.edit_username, R.id.edit_email})
    public void setTextListener(CharSequence charSequence, int start, int before, int count){
        String number = charSequence.toString().length() +"/30";
        text.setText(number);
    }

    @OnFocusChange({R.id.edit_full_name, R.id.edit_password, R.id.edit_username, R.id.edit_email})
    public void onFocusChange(View view, boolean inFocus){
        switch (view.getId()){
            case R.id.edit_full_name:
                if(inFocus){
                    text = (TextView)findViewById(R.id.text_full_name);
                    text.setVisibility(View.VISIBLE);
                }else {
                    text.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.edit_username:
                if(inFocus){
                    text = (TextView)findViewById(R.id.text_username);
                    text.setVisibility(View.VISIBLE);
                }else {
                    text.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.edit_email:
                if(inFocus){
                    text = (TextView)findViewById(R.id.text_email);
                    text.setVisibility(View.VISIBLE);
                }else {
                    text.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.edit_password:
                if(inFocus){
                    text = (TextView)findViewById(R.id.text_password);
                    text.setVisibility(View.VISIBLE);
                }else {
                    text.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }
}
