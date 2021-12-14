package com.cookandroid.moamoa;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MoAMoA_Login extends AppCompatActivity {

    private static String IP_ADDRESS = "10.0.2.2";
    private static String TAG = "phptest";
    public static MoAMoA_Login LoginActivity;
    private String userId;
    private TextView resultText;

    protected String getUserId() {
        return userId;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moamoa_login);

        Button loginButton = (Button) findViewById(R.id.login_button);
        Button resistButton = (Button) findViewById(R.id.go_resist);

        EditText idText = (EditText)findViewById(R.id.editTextId);
        EditText passwordText = (EditText) findViewById(R.id.editTextPassword);
        resultText = (TextView) findViewById(R.id.resultText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idText.getText().toString();
                String password = passwordText.getText().toString();

                if(idText.getText().toString() == null || passwordText.getText().toString() == null) {
                    Toast.makeText(getApplicationContext(), "id, password를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //openMain();
                loginData task = new loginData();
                task.execute("http://" + IP_ADDRESS + "/login.php", id, password);
            }
        });

        resistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MoAMoA_Resist.class);
                intent.putExtra("id", getUserId());
                startActivity(intent);
            }
        });
    }

    private void openMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("UserId", userId);

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
            super.onPostExecute(result);

            progressDialog.dismiss();
            resultText.setText(result);

            if (result.equals("로그인 성공")) {
                openMain();
            }
            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }

        protected String doInBackground(String... params) {
            String id = (String)params[1];
            userId = id;
            String password = (String)params[2];

            String serverURL = (String)params[0];
            String postParameters = "id=" + id + "&password=" + password;

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString();
            }
            catch (Exception e) {
                Log.d(TAG, "SelectData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }
    }
}
