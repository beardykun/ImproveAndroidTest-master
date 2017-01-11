package com.mikhail.pankratov.improveandroidtest.remind;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.mikhail.pankratov.improveandroidtest.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RemindPasswordActivity extends AppCompatActivity implements RemindPasswordView{

    private static final int ERROR_SHOW_TIMEOUT = 2000;

    @BindView(R.id.email_check_text_input)TextInputLayout emailCheckTextInput;
    @BindView(R.id.email_check_edit)TextInputEditText emailCheckEdit;

    RemindPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_password);

        ButterKnife.bind(this);
        presenter = new RemindPresenterImpl();
    }

    @OnClick(R.id.remind_button)
    public void onRemindClick(){
        presenter.onRemind(emailCheckEdit.getText().toString(), this);
    }

    @Override
    public void showEmailInputError() {
        emailCheckTextInput.setErrorEnabled(true);
        emailCheckTextInput.setError(getString(R.string.email_check_error));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                emailCheckTextInput.setErrorEnabled(false);
                emailCheckTextInput.setError("");
            }
        }, ERROR_SHOW_TIMEOUT);
    }

    @Override
    public void onRemindSuccess(final Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RemindPasswordActivity.this);
        builder.setTitle(getString(R.string.alert_title)).setMessage(getString(R.string.alert_message))
                .setCancelable(false).setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(intent);
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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
}
