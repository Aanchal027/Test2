package com.androidjson.sqlitelogin_androidjsoncom;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.sqlitelogin_androidjsoncom.database.DatabaseManagerUser;

public class MainActivity extends AppCompatActivity {
    private EditText eEmail, ePassword;
    private Button register;
    private  ImageButton login;
    private String email;
    private String password;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eEmail = findViewById(R.id.editEmail);
        ePassword = findViewById(R.id.editPassword);
        login = findViewById(R.id.buttonLogin);
        register = findViewById(R.id.buttonRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               start();
            }
        });
    }

    private void start() {

        if (!validar()) return;

        email = eEmail.getText().toString();
        password = ePassword.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Starting...");
        progressDialog.show();

        final DatabaseManagerUser databaseManager = new DatabaseManagerUser(getApplicationContext());

        eEmail.getText().clear();
        ePassword.getText().clear();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        if (databaseManager.checkregister(email)){
                            cursor = databaseManager.getDb().rawQuery("SELECT email, password FROM demo" + " WHERE email='"+email+"' AND password='"+password+"'",null);
                            if(cursor.moveToFirst()){
                                Intent intent =new Intent(getApplicationContext(),DashboardActivity.class);
                                intent.putExtra("IDENT",email);
                                startActivity(intent);
                                finish();
                                progressDialog.dismiss();
                            }else{
                                eEmail.setText(email);
                                progressDialog.dismiss();
                                String mesg = String.format("Password invalid", null);
                                Toast.makeText(getApplicationContext(),mesg, Toast.LENGTH_LONG).show();
                            }
                        }else{
                            progressDialog.dismiss();
                            String mesg = String.format("The email you entered does not match any account", null);
                            Toast.makeText(getApplicationContext(),mesg, Toast.LENGTH_LONG).show();
                        }
                    }
                }, 3000);

    }

    private boolean validar() {
        boolean valid = true;

        String email = eEmail.getText().toString();
        String password = ePassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            eEmail.setError("Enter a Valid E-mail address");
            valid = false;
        } else {
            eEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            ePassword.setError("Enter 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            ePassword.setError(null);
        }

        return valid;
    }
}
