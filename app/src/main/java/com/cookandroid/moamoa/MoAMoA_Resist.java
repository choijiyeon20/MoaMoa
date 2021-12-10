package com.cookandroid.moamoa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MoAMoA_Resist extends AppCompatActivity {

    private static String IP_ADDRESS = "10.0.2.2";
    private static String TAG = "phptest";

    // 안드로이드 이름, 국가, 결과 텍스트뷰
    private EditText mEditTextID;
    private EditText mEditTextPassword;
    private EditText mEditTextPasswordRetry;
    private EditText mEditTextName;
    private EditText mEditTextNickname;
    private TextView mTextViewResult;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moamoa_resist);

        // 해당하는 친구들 납치
        mEditTextID = (EditText)findViewById(R.id.resist_Id);
        mEditTextPassword = (EditText)findViewById(R.id.resist_Password);
        mEditTextPasswordRetry = (EditText)findViewById(R.id.resist_PasswordRe);
        mEditTextName = (EditText)findViewById(R.id.resist_Name);
        mEditTextNickname = (EditText)findViewById(R.id.resist_NickName);
        mTextViewResult = (TextView)findViewById(R.id.resist_result);

        // ???
        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

        Button resistButton = (Button) findViewById(R.id.resist_button);
        Button backButton = (Button) findViewById(R.id.resist_back);

        // back 뒤로가기 버튼
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // resist 가입 부분
        resistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 문자열로 값 가져오기
                String id = mEditTextID.getText().toString();
                String password = mEditTextPassword.getText().toString();
                String passwordRe = mEditTextPasswordRetry.getText().toString();
                String name = mEditTextName.getText().toString();
                String nickname = mEditTextNickname.getText().toString();

                if (password.equals(passwordRe)) {
                    InsertData task = new InsertData();
                    task.execute("http://" + IP_ADDRESS + "/resist.php", id, password, name, nickname);

                    // 입력 후 값 비워주기
                    //mEditTextID.setText("");
                    //mEditTextPassword.setText("");
                    //mEditTextPasswordRetry.setText("");
                } else {
                    mTextViewResult.setText("비밀번호가 다릅니다.");
                }
            }
        });
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoAMoA_Resist.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
            if (result.equals("회원가입 성공!")) {
                Toast.makeText(getApplicationContext(), "회원가입 성공!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String id = (String)params[1];
            String password = (String)params[2];
            String name = (String)params[3];
            String nickname = (String)params[4];

            String serverURL = (String)params[0];
            String postParameters = "id=" + id + "&password=" + password + "&name=" + name + "&nickname=" + nickname;


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


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }
    }
}
