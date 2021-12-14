package com.cookandroid.moamoa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class mypage_change_password extends AppCompatActivity {

    private static String IP_ADDRESS = "10.0.2.2";
    private static String TAG = "phptest";
    private EditText nowPassword;
    private EditText newPassword;
    private EditText newPasswordRe;
    private TextView changePasswordResult;
    private String userId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_change_password);

        Button backbtn = (Button) findViewById(R.id.change_back);

        nowPassword = (EditText) findViewById(R.id.now_password);
        newPassword = (EditText) findViewById(R.id.new_password);
        newPasswordRe = (EditText) findViewById(R.id.new_passwordRe);
        changePasswordResult = (TextView) findViewById(R.id.change_password_result);

        Intent receive_id = getIntent();
        userId = receive_id.getStringExtra("id");

        Button changeBtn = (Button) findViewById(R.id.change_password_button);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nowpassword = nowPassword.getText().toString();
                String newpassword = newPassword.getText().toString();
                String newpasswordre = newPasswordRe.getText().toString();

                if (newpassword.equals(newpasswordre)) {
                    updatePasswordData task = new updatePasswordData();
                    task.execute("http://" + IP_ADDRESS + "/change_password.php", userId, nowpassword, newpassword);
                } else {
                    changePasswordResult.setText("비밀번호가 맞지 않습니다.");
                }
            }
        });
    }

    class updatePasswordData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(mypage_change_password.this,
                    "Please Wait", null, true, true);
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            changePasswordResult.setText(result);
            if (result.equals("성공적으로 변경했어요!")) {
                finish();
            }
        }

        protected String doInBackground(String... params) {
            String id = (String)params[1];
            String nowpassword = (String)params[2];
            String newpassword = (String)params[3];

            String serverURL = (String)params[0];
            String postParameters = "id=" + id + "&nowpassword=" + nowpassword + "&newpassword=" + newpassword;

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

//    private View view;

    //네비바 외의 프레그먼트와 연결할 때 꼭 필요한 newlnstnce() 메소드
//    public static mypage_change_password newlnstnce(){
//        return new mypage_change_password();
//    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.mypage_change_password, container, false);
//
//        //FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//
//        Button Backbtn = (Button) view.findViewById(R.id.change_back);
//        Backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((MainActivity)getActivity()).replaceFragment(Main_mypage.newlnstnce());
//                //activity.onFragmentChange(1);
//                /* 액티비티일 때 띄우기
//                Intent intent = new Intent(getActivity(), Board.class);
//                startActivity(intent);*/
//            }
//        });
//        return view;
//    }
}