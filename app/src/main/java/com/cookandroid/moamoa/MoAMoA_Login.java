package com.cookandroid.moamoa;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MoAMoA_Login extends AppCompatActivity {

    private static String IP_ADDRESS = "10.0.2.2";
    public static MoAMoA_Login LoginActivity;
    private String userId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moamoa_login);

        Button loginButton = (Button) findViewById(R.id.login_button);
        Button resistButton = (Button) findViewById(R.id.go_resist);

        EditText idText = (EditText)findViewById(R.id.editTextId);
        EditText passwordText = (EditText) findViewById(R.id.editTextPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idText.getText().toString();
                String password = passwordText.getText().toString();

                if(idText.getText().toString() == null || passwordText.getText().toString() == null) {
                    Toast.makeText(getApplicationContext(), "id, password를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                openMain();
                //MoAMoA_Login.loginData task = new MoAMoA_Login.loginData();
                //task.execute("http://" + IP_ADDRESS + "/resist.php", id, password);
            }
        });
        resistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MoAMoA_Resist.class);
                startActivity(intent);
            }
        });
    }

    private void openMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        LoginActivity = MoAMoA_Login.this;
        startActivity(intent);
    }

    class loginData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoAMoA_Login.this,
                    "Please Wait", null, true, true);
        }

        protected void onPostExecute(String result) {
            super.onPreExecute();

            progressDialog.dismiss();
        }

        protected String doInBackground(String... params) {
            String id = (String)params[1];
            String password = (String)params[2];

            String serverURL = (String)params[0];
            String postParameters = "id=" + id + "&password=" + password;

            try {
                return "";
            }
            catch (Exception e) {
                return "";
            }
        }
    }
}
